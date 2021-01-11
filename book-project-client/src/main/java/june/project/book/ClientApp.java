package june.project.book;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import june.project.book.handler.BookBasketAddCommand;
import june.project.book.handler.BookBasketDeleteCommand;
import june.project.book.handler.BookBasketDetailCommand;
import june.project.book.handler.BookBasketListCommand;
import june.project.book.handler.BookBasketUpdateCommand;
import june.project.book.handler.BookBoardAddCommand;
import june.project.book.handler.BookBoardDeleteCommand;
import june.project.book.handler.BookBoardDetailCommand;
import june.project.book.handler.BookBoardListCommand;
import june.project.book.handler.BookBoardUpdateCommand;
import june.project.book.handler.BookmarkAddCommand;
import june.project.book.handler.BookmarkDeleteCommand;
import june.project.book.handler.BookmarkDetailCommand;
import june.project.book.handler.BookmarkListCommand;
import june.project.book.handler.BookmarkUpdateCommand;
import june.project.book.handler.Command;
import june.project.book.handler.MemberAddCommand;
import june.project.book.handler.MemberDeleteCommand;
import june.project.book.handler.MemberDetailCommand;
import june.project.book.handler.MemberListCommand;
import june.project.book.handler.MemberUpdateCommand;
import june.project.util.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {

    String serverAddr = null;
    int port = 0;

    try {

      // 사용자로부터 접속할 서버의 주소와 포트 번호를 입력 받는다.
      serverAddr = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyboard.close();
      return;
    }


    try (
        // 서버와 연결
        Socket socket = new Socket(serverAddr, port);

        // 소켓을 통해 데이터를 읽고 쓰는 도구를 준비한다.
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");

      processCommand(out, in);

      System.out.println("서버와 연결을 끊었음!");

    } catch (Exception e) {
      System.out.println("예외 발생");
      e.printStackTrace();
    }
    keyboard.close();

  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();


    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/basket/add", new BookBasketAddCommand(out, in, prompt));
    commandMap.put("/basket/list", new BookBasketListCommand(out, in));
    commandMap.put("/basket/detail", new BookBasketDetailCommand(out, in, prompt));
    commandMap.put("/basket/update", new BookBasketUpdateCommand(out, in, prompt));
    commandMap.put("/basket/delete", new BookBasketDeleteCommand(out, in, prompt));

    commandMap.put("/bookmark/add", new BookmarkAddCommand(out, in, prompt));
    commandMap.put("/bookmark/list", new BookmarkListCommand(out, in));
    commandMap.put("/bookmark/detail", new BookmarkDetailCommand(out, in, prompt));
    commandMap.put("/bookmark/update", new BookmarkUpdateCommand(out, in, prompt));
    commandMap.put("/bookmark/delete", new BookmarkDeleteCommand(out, in, prompt));

    commandMap.put("/book/add", new BookBoardAddCommand(out, in, prompt));
    commandMap.put("/book/list", new BookBoardListCommand(out, in));
    commandMap.put("/book/detail", new BookBoardDetailCommand(out, in, prompt));
    commandMap.put("/book/update", new BookBoardUpdateCommand(out, in, prompt));
    commandMap.put("/book/delete", new BookBoardDeleteCommand(out, in, prompt));

    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));

    try {
      while (true) {
        String command;
        command = prompt.inputString("\n명령> ");

        if (command.length() == 0)
          continue;

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("안녕!");
          break;

        } else if (command.equals("history")) {
          printCommandHistory(commandStack.iterator());
          continue;

        } else if (command.equals("history2")) {
          printCommandHistory(commandQueue.iterator());
          continue;
        }

        commandStack.push(command);
        commandQueue.offer(command);

        Command commandHandler = commandMap.get(command);

        if (commandHandler != null) {
          try {
            commandHandler.execute();
          } catch (Exception e) {
            System.out.printf("명령어 실행중 오류 발생: %s \n", e.getMessage());
          }
        } else {
          System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    } catch (Exception e) {
      System.out.println("프로그램 실행 중 오류 발생!");
    }
    keyboard.close();
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

  public static void main(String[] args) {
    System.out.println("클라이언트 책 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();
  }
}
