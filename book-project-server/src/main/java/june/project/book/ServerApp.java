package june.project.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.BookBoardDao;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.MemberDao;
import june.project.book.servlet.BookBoardAddServlet;
import june.project.book.servlet.BookBoardDeleteServlet;
import june.project.book.servlet.BookBoardDetailServlet;
import june.project.book.servlet.BookBoardListServlet;
import june.project.book.servlet.BookBoardUpdateServlet;
import june.project.book.servlet.BookmarkAddServlet;
import june.project.book.servlet.BookmarkDeleteServlet;
import june.project.book.servlet.BookmarkDetailServlet;
import june.project.book.servlet.BookmarkListServlet;
import june.project.book.servlet.BookmarkUpdateServlet;
import june.project.book.servlet.MemberAddServlet;
import june.project.book.servlet.MemberDeleteServlet;
import june.project.book.servlet.MemberDetailServlet;
import june.project.book.servlet.MemberListServlet;
import june.project.book.servlet.MemberUpdateServlet;
import june.project.book.servlet.Servlet;

public class ServerApp {

  // 옵저버 목록을 관리할 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  // 스레드풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  // 옵저버를 등록하는 메서드이다.
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  // 옵저버를 제거하는 메서드이다.
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  // 애플리케이션이 시작되면, 등록된 리스너에게 알린다.
  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  // 애플리케이션이 종료되면, 등록된 리스너에게 알린다.
  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {

    notifyApplicationInitialized();

    BookBoardDao bookBoardDao = (BookBoardDao) context.get("bookBoardDao");
    BookmarkDao bookmarkDao = (BookmarkDao) context.get("bookmarkDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");

    servletMap.put("/book/list", new BookBoardListServlet(bookBoardDao));
    servletMap.put("/book/add", new BookBoardAddServlet(bookBoardDao));
    servletMap.put("/book/detail", new BookBoardDetailServlet(bookBoardDao));
    servletMap.put("/book/update", new BookBoardUpdateServlet(bookBoardDao));
    servletMap.put("/book/delete", new BookBoardDeleteServlet(bookBoardDao));

    servletMap.put("/bookmark/list", new BookmarkListServlet(bookmarkDao));
    servletMap.put("/bookmark/add", new BookmarkAddServlet(bookmarkDao));
    servletMap.put("/bookmark/detail", new BookmarkDetailServlet(bookmarkDao));
    servletMap.put("/bookmark/update", new BookmarkUpdateServlet(bookmarkDao));
    servletMap.put("/bookmark/delete", new BookmarkDeleteServlet(bookmarkDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));

    try (
        // 서버쪽 연결 준비
        // => 클라이언트의 연결을 9999번 포트에서 기다린다.
        ServerSocket serverSocket = new ServerSocket(9999);) {

      System.out.println("클라이언트 연결 대기중....");

      while (true) {
        // 서버에 대기하고 있는 클라이언트와 연결
        // => 대기하고 있는 클라이언트와 연결될 때까지 리턴하지 않는다.
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        // 스레드풀을 사용할 때는 직접 스레드를 만들지 않는다.
        // 단지 스레드풀에 "스레드가 실행할 코드(Runnable 구현체)"를 제출한다.
        // => ExcutorService.submit(new Runnable() { public void run() {..});
        // => 스레드풀에 스레드가 없으면 새로 만들어 Runnable 구현체를 실행한다.
        // => 스레드풀에 스레드가 있으면 그 스레드를 이용하여 Runnable 구현체를 실행한다.
        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("------------------요청처리 끝--------------------");
        });
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();

    // 스레드풀을 다 사용했으면 종료
    executorService.shutdown();
    // => 스레드풀을 당장 종료시키는 것이 아니다.
    // => 스레드풀에 소속된 스레드들의 작업이 모두 끝나면 종료하는 뜻

  } // service()


  int processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        // 클라이언트의 메시지를 수신하고 클라이언트로 전송할 입출력 도구 준비
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      String request = in.readUTF(); // 요청 메시지를 읽는다.
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return 9;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.writeUTF("FAIL");
          out.writeUTF(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }

      out.flush();
      System.out.println("클라이언트에게 응답하였음!");

      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  } // prcessRequest()

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("서버 책 관리 시스템입니다");

    ServerApp app = new ServerApp();

    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
