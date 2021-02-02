package june.project.book;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.MemberDao;
import june.project.book.dao.mariadb.BookBoardDaoImpl;
import june.project.book.dao.mariadb.BookmarkDaoImpl;
import june.project.book.dao.mariadb.MemberDaoImpl;
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

  Deque<String> commandStack;
  Queue<String> commandQueue;

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() {

    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    // Dao 프록시 객체 준비
    BookBoardDao bookBoardDao = new BookBoardDaoImpl();
    BookmarkDao bookmarkDao = new BookmarkDaoImpl();
    MemberDao memberDao = new MemberDaoImpl();

    commandMap.put("/bookmark/add", new BookmarkAddCommand(bookmarkDao, prompt));
    commandMap.put("/bookmark/list", new BookmarkListCommand(bookmarkDao));
    commandMap.put("/bookmark/detail", new BookmarkDetailCommand(bookmarkDao, prompt));
    commandMap.put("/bookmark/update", new BookmarkUpdateCommand(bookmarkDao, prompt));
    commandMap.put("/bookmark/delete", new BookmarkDeleteCommand(bookmarkDao, prompt));

    commandMap.put("/book/add", new BookBoardAddCommand(bookBoardDao, prompt));
    commandMap.put("/book/list", new BookBoardListCommand(bookBoardDao));
    commandMap.put("/book/detail", new BookBoardDetailCommand(bookBoardDao, prompt));
    commandMap.put("/book/update", new BookBoardUpdateCommand(bookBoardDao, prompt));
    commandMap.put("/book/delete", new BookBoardDeleteCommand(bookBoardDao, prompt));

    commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
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

      // 명령어를 받아서 넘긴다.
      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);

    }
    keyboard.close();
  }

  private void processCommand(String command) {

    Command commandHandler = commandMap.get(command);
    if (commandHandler == null) {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }
    commandHandler.execute();
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
