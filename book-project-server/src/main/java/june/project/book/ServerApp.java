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
import june.project.book.domain.BookBasket;
import june.project.book.domain.BookBoard;
import june.project.book.domain.Bookmark;
import june.project.book.domain.Member;
import june.project.book.servlet.BookAddServlet;
import june.project.book.servlet.BookBasketAddServlet;
import june.project.book.servlet.BookBasketDeleteServlet;
import june.project.book.servlet.BookBasketDetailServlet;
import june.project.book.servlet.BookBasketListServlet;
import june.project.book.servlet.BookBasketUpdateServlet;
import june.project.book.servlet.BookDeleteServlet;
import june.project.book.servlet.BookDetailServlet;
import june.project.book.servlet.BookListServlet;
import june.project.book.servlet.BookUpdateServlet;
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

  @SuppressWarnings("unchecked")
  public void service() {

    notifyApplicationInitialized();

    bookBoard = (List<BookBoard>) context.get("bookBoardList");
    bookmarks = (List<Bookmark>) context.get("bookmarkList");
    bookBasketList = (List<BookBasket>) context.get("bookBasketList");
    memberList = (List<Member>) context.get("memberList");

    servletMap.put("/book/list", new BookListServlet(bookBoard));
    servletMap.put("/book/add", new BookAddServlet(bookBoard));
    servletMap.put("/book/detail", new BookDetailServlet(bookBoard));
    servletMap.put("/book/update", new BookUpdateServlet(bookBoard));
    servletMap.put("/book/delete", new BookDeleteServlet(bookBoard));

    servletMap.put("/bookmark/list", new BookmarkListServlet(bookmarks));
    servletMap.put("/bookmark/add", new BookmarkAddServlet(bookmarks));
    servletMap.put("/bookmark/detail", new BookmarkDetailServlet(bookmarks));
    servletMap.put("/bookmark/update", new BookmarkUpdateServlet(bookmarks));
    servletMap.put("/bookmark/delete", new BookmarkDeleteServlet(bookmarks));

    servletMap.put("/basket/list", new BookBasketListServlet(bookBasketList));
    servletMap.put("/basket/add", new BookBasketAddServlet(bookBasketList));
    servletMap.put("/basket/detail", new BookBasketDetailServlet(bookBasketList));
    servletMap.put("/basket/update", new BookBasketUpdateServlet(bookBasketList));
    servletMap.put("/basket/delete", new BookBasketDeleteServlet(bookBasketList));

    servletMap.put("/member/list", new MemberListServlet(memberList));
    servletMap.put("/member/add", new MemberAddServlet(memberList));
    servletMap.put("/member/detail", new MemberDetailServlet(memberList));
    servletMap.put("/member/update", new MemberUpdateServlet(memberList));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberList));

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
        System.out.println("클라이언트로 메시지를 전송하였음!");
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
