package june.project.book;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import june.project.book.context.ApplicationContextListener;
import june.project.sql.SqlSessionFactoryProxy;
import june.project.util.RequestHandler;
import june.project.util.RequestMappingHandlerMapping;

public class ServerApp {

  // 옵저버 목록을 관리할 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  // 스레드풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  // 서버 멈춤 여부 설정 변수
  boolean serverStop = false;

  // IoC 컨테이너 준비
  ApplicationContext iocContainer;

  // request handler 맵퍼 준비
  RequestMappingHandlerMapping handlerMapper;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {

    notifyApplicationInitialized();

    // ApplicationContext (IoC 컨테이너)를 꺼낸다.
    iocContainer = (ApplicationContext) context.get("iocContatiner");

    // request handler mapper를 꺼낸다.
    handlerMapper = (RequestMappingHandlerMapping) context.get("handlerMapper");

    // IoC 컨테이너에서 SqlSessionFactory를 꺼낸다.
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) iocContainer.getBean("sqlSessionFactory");

    try (ServerSocket serverSocket = new ServerSocket(9999);) {

      System.out.println("클라이언트 연결 대기중....");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        executorService.submit(() -> {
          processRequest(socket);

          // 스레드에 보관된 SqlSession 객체를 제거한다.
          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
          System.out.println("------------------요청처리 끝--------------------");
        });

        if (serverStop) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    executorService.shutdown();

    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        // 0.5초 마다 깨어나서 스레드 종료 여부를 검사한다.
        Thread.sleep(500);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // 클라이언트 요청을 처리하는 스레드가 모두 종료된 후에 DB 커넥션을 닫도록 한다.
    notifyApplicationDestroyed();

    System.out.println("서버 종료!");

  } // service()


  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);


      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(request);

      if (requestHandler != null) {
        try {
          requestHandler.getMethod().invoke(requestHandler.getBean(), in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }

      out.println("!end!");
      out.flush();
      System.out.println("클라이언트에게 응답하였음!");

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
    }
  } // prcessRequest()

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("서버 책 관리 시스템입니다");

    ServerApp app = new ServerApp();

    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}
