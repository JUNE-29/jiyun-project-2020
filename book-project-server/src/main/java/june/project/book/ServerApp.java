package june.project.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.json.BookBasketJsonFileDao;
import june.project.book.dao.json.BookBoardJsonFileDao;
import june.project.book.dao.json.BookmarkJsonFileDao;
import june.project.book.dao.json.MemberJsonFileDao;
import june.project.book.domain.BookBasket;
import june.project.book.domain.BookBoard;
import june.project.book.domain.Bookmark;
import june.project.book.domain.Member;
import june.project.book.servlet.BookBasketAddServlet;
import june.project.book.servlet.BookBasketDeleteServlet;
import june.project.book.servlet.BookBasketDetailServlet;
import june.project.book.servlet.BookBasketListServlet;
import june.project.book.servlet.BookBasketUpdateServlet;
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

  List<BookBoard> bookBoard;
  List<Bookmark> bookmarks;
  List<BookBasket> bookBasketList;
  List<Member> memberList;

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

    BookBoardJsonFileDao bookBoardDao = (BookBoardJsonFileDao) context.get("bookBoardDao");
    BookmarkJsonFileDao bookmarkDao = (BookmarkJsonFileDao) context.get("bookmarkDao");
    BookBasketJsonFileDao bookBaskeDao = (BookBasketJsonFileDao) context.get("bookBasketDao");
    MemberJsonFileDao memberDao = (MemberJsonFileDao) context.get("memberList");

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

    servletMap.put("/basket/list", new BookBasketListServlet(bookBaskeDao));
    servletMap.put("/basket/add", new BookBasketAddServlet(bookBaskeDao));
    servletMap.put("/basket/detail", new BookBasketDetailServlet(bookBaskeDao));
    servletMap.put("/basket/update", new BookBasketUpdateServlet(bookBaskeDao));
    servletMap.put("/basket/delete", new BookBasketDeleteServlet(bookBaskeDao));

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

        // 클라이언트의 요청처리
        if (processRequest(socket) == 9) {
          break;
        }

        System.out.println("------------------요청처리 끝--------------------");
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();

  } // service()


  int processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        // 클라이언트의 메시지를 수신하고 클라이언트로 전송할 입출력 도구 준비
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        String request = in.readUTF(); // 요청 메시지를 읽는다.
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "/sever/stop":
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
      }

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
