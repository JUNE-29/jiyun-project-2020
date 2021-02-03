package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardListServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardListServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<BookBoard> bookBoard = bookBoardDao.findAll();
    for (BookBoard book : bookBoard) {
      System.out.printf("%d, %s, %d점, %s, %d\n", //
          book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(), book.getBookStatus());
    }
  }
}
