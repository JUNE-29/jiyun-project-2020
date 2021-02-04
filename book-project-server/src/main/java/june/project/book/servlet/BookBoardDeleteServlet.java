package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;

public class BookBoardDeleteServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardDeleteServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (bookBoardDao.delete(no) > 0) {
      out.println("삭제했습니다.");

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
