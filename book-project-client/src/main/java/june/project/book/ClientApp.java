package june.project.book;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import june.project.util.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {

    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0)
        continue;

      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;

      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      // 명령어를 받아서 넘긴다.
      processCommand(command);

      if (command.endsWith("/server/stop")) {
        // 서버는 다음 클라이언트 요청이 들어 올 때 처리할 것이다.
        // 이를 즉시 처리하도록 하기 위해
        // 임의 요청을 한 번 더 보낸다.
        processCommand(command);
      }
    }
    keyboard.close();
  }

  private void processCommand(String command) {
    // 명령어 형식을 변경한다!
    // [기존방식]
    // => 예) /book/list
    // [새로운 방식]
    // => 예) bitcamp://서버주소:포트번호/book/list
    //
    String host = null;
    int port = 9999;
    String servletPath = null;

    try {
      if (!command.startsWith("bitcamp://")) {
        throw new Exception("명령어 형식이 옳지 않습니다!");
      }

      // System.out.println(command);
      // command 예) bitcamp://localhost:9999/book/list
      String url = command.substring(10);
      // => localhost:9999/book/list

      // System.out.println(url);

      int index = url.indexOf('/');
      String[] str = //
          url.substring(0, index) // localhost:9999
              .split(":"); // {"localhost","9999"}
      // substring 호출되서 스트링으로 리턴된 것을 나눈다.

      host = str[0];
      if (str.length == 2) {
        port = Integer.parseInt(str[1]);
      }
      // System.out.printf("=> %s:%d\n", host, port); // => localhost:9999

      servletPath = url.substring(index); // => /book/list
      // System.out.printf("=> %s\n", servletPath);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      // 서버에 명령을 보낸다.
      out.println(servletPath);
      out.flush();

      // 서버의 응답을 읽어서 출력한다.
      while (true) {
        String response = in.nextLine();
        if (response.equals("!end!")) {
          break;

        } else if (response.equals("!{}!")) {
          String input = prompt.inputString("");
          out.println(input);

        } else {
          System.out.println(response);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }



  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 책 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();
  }
}
