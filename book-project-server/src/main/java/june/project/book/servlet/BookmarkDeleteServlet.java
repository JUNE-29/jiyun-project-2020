package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;

public class BookmarkDeleteServlet implements Servlet {

  BookmarkDao bookmarkDao;

  public BookmarkDeleteServlet(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (bookmarkDao.delete(no) > 0) {
      out.println("삭제했습니다.");

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
