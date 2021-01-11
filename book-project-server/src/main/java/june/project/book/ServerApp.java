package june.project.book;

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

public class ServerApp {

  // 옵저버 목록을 관리할 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

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


  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        // 클라이언트의 메시지를 수신하고 클라이언트로 전송할 입출력 도구 준비
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        // 클라이언트가 보낸 메시지를 수신한다.
        String request = in.readUTF(); // 요청 메시지를 읽는다.
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<BookBoard> bookBoard = (List<BookBoard>) context.get("bookBoardList");
        List<Bookmark> bookmarks = (List<Bookmark>) context.get("bookmarkList");
        List<BookBasket> bookBasketList = (List<BookBasket>) context.get("bookBasketList");
        List<Member> memberList = (List<Member>) context.get("memberList");

        // 클라이언트의 명령에 응답을 한다.
        if (request.equals("/book/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(bookBoard);

        } else if (request.equals("/book/add")) {
          try {
            BookBoard book = (BookBoard) in.readObject();

            int i = 0;
            for (; i < bookBoard.size(); i++) {
              if (bookBoard.get(i).getNo() == book.getNo()) {
                break;
              }
            }

            if (i == bookBoard.size()) {
              bookBoard.add(book);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/book/detail")) {
          try {
            int no = in.readInt();

            BookBoard book = null;
            for (BookBoard b : bookBoard) {
              if (b.getNo() == no) {
                book = b;
                break;
              }
            }

            if (book != null) {
              out.writeUTF("OK");
              out.writeObject(book);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/book/update")) {
          try {
            BookBoard book = (BookBoard) in.readObject();

            int index = -1;
            for (int i = 0; i < bookBoard.size(); i++) {
              if (bookBoard.get(i).getNo() == book.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookBoard.set(index, book);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/book/delete")) {
          try {

            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < bookBoard.size(); i++) {
              if (bookBoard.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookBoard.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/bookmark/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(bookmarks);

        } else if (request.equals("/bookmark/add")) {
          try {
            Bookmark bookmark = (Bookmark) in.readObject();

            int i = 0;
            for (; i < bookmarks.size(); i++) {
              if (bookmarks.get(i).getNo() == bookmark.getNo()) {
                break;
              }
            }

            if (i == bookmarks.size()) {
              bookmarks.add(bookmark);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/bookmark/detail")) {
          try {

            int no = in.readInt();

            Bookmark bookmark = null;
            for (Bookmark bm : bookmarks) {
              if (bm.getNo() == no) {
                bookmark = bm;
                break;
              }
            }

            if (bookmark != null) {
              out.writeUTF("OK");
              out.writeObject(bookmark);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/bookmark/update")) {
          try {

            Bookmark bookmark = (Bookmark) in.readObject();

            int index = -1;
            for (int i = 0; i < bookmarks.size(); i++) {
              if (bookmarks.get(i).getNo() == bookmark.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookmarks.set(index, bookmark);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/bookmark/delete")) {
          try {

            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < bookmarks.size(); i++) {
              if (bookmarks.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookmarks.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/basket/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(bookBasketList);

        } else if (request.equals("/basket/add")) {
          try {
            BookBasket bookBasket = (BookBasket) in.readObject();

            int i = 0;
            for (; i < bookBasketList.size(); i++) {
              if (bookBasketList.get(i).getNo() == bookBasket.getNo()) {
                break;
              }
            }

            if (i == bookBasketList.size()) {
              bookBasketList.add(bookBasket);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/basket/detail")) {
          try {
            int no = in.readInt();

            BookBasket bookBasket = null;
            for (BookBasket bb : bookBasketList) {
              if (bb.getNo() == no) {
                bookBasket = bb;
              }
            }

            if (bookBasket != null) {
              out.writeUTF("OK");
              out.writeObject(bookBasket);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/basket/update")) {
          try {

            BookBasket bookBasket = (BookBasket) in.readObject();

            int index = -1;
            for (int i = 0; i < bookBasketList.size(); i++) {
              if (bookBasketList.get(i).getNo() == bookBasket.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookBasketList.set(index, bookBasket);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/basket/delete")) {
          try {

            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < bookBasketList.size(); i++) {
              if (bookBasketList.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              bookBasketList.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(memberList);

        } else if (request.equals("/member/add")) {

          try {
            Member member = (Member) in.readObject();

            int i = 0;
            for (; i < memberList.size(); i++) {
              if (memberList.get(i).getNo() == member.getNo()) {
                break;
              }
            }
            if (i == memberList.size()) {
              memberList.add(member);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/detail")) {

          try {
            int no = in.readInt();

            Member member = null;
            for (Member m : memberList) {
              if (m.getNo() == no) {
                member = m;
                break;
              }
            }

            if (member != null) {
              out.writeUTF("OK");
              out.writeObject(member);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/update")) {
          try {
            Member member = (Member) in.readObject();

            int index = -1;
            for (int i = 0; i < memberList.size(); i++) {
              if (memberList.get(i).getNo() == member.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              memberList.set(index, member);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/delete")) {
          try {
            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < memberList.size(); i++) {
              if (memberList.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              memberList.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리할 수 없습니다.");
        }
        out.flush();
      }

      System.out.println("클라이언트로 메시지를 전송하였음!");
      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  } // prcessRequest()

  public static void main(String[] args) {
    System.out.println("서버 책 관리 시스템입니다");

    ServerApp app = new ServerApp();

    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
