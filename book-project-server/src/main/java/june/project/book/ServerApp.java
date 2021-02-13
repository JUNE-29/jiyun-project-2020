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
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.BookBoardDao;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.MemberDao;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
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
import june.project.book.servlet.LoginServlet;
import june.project.book.servlet.MemberAddServlet;
import june.project.book.servlet.MemberDeleteServlet;
import june.project.book.servlet.MemberDetailServlet;
import june.project.book.servlet.MemberListServlet;
import june.project.book.servlet.MemberSearchServlet;
import june.project.book.servlet.MemberUpdateServlet;
import june.project.book.servlet.PhotoBoardAddServlet;
import june.project.book.servlet.PhotoBoardDeleteServlet;
import june.project.book.servlet.PhotoBoardDetailServlet;
import june.project.book.servlet.PhotoBoardListServlet;
import june.project.book.servlet.PhotoBoardUpdateServlet;
import june.project.book.servlet.Servlet;
import june.project.sql.PlatformTransactionManager;
import june.project.util.DataSource;

public class ServerApp {

  // 옵저버 목록을 관리할 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  // 스레드풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  // 서버 멈춤 여부 설정 변수
  boolean serverStop = false;

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

    // ConnectionFactory 꺼낸다.
    DataSource dataSource = (DataSource) context.get("dataSource");

    // 트랜잭션 관리자를 꺼내 변수에 저장한다.
    PlatformTransactionManager txManager =
        (PlatformTransactionManager) context.get("transactionManager");

    BookBoardDao bookBoardDao = (BookBoardDao) context.get("bookBoardDao");
    BookmarkDao bookmarkDao = (BookmarkDao) context.get("bookmarkDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

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
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));

    servletMap.put("/photoboard/list", new PhotoBoardListServlet(photoBoardDao, bookmarkDao));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(photoBoardDao));
    servletMap.put("/photoboard/add",
        new PhotoBoardAddServlet(txManager, photoBoardDao, bookmarkDao, photoFileDao));
    servletMap.put("/photoboard/update",
        new PhotoBoardUpdateServlet(txManager, photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/delete",
        new PhotoBoardDeleteServlet(txManager, photoBoardDao, photoFileDao));

    servletMap.put("/auth/login", new LoginServlet(memberDao));

    try (ServerSocket serverSocket = new ServerSocket(9999);) {

      System.out.println("클라이언트 연결 대기중....");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        executorService.submit(() -> {
          processRequest(socket);

          dataSource.removeConnection();
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
        // 클라이언트의 메시지를 수신하고 클라이언트로 전송할 입출력 도구 준비
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);


      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

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

    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
