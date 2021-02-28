package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.Component;

@Component("/book/list")
public class BookBoardListServlet implements Servlet {

  BookBoardService bookBoardService;

  public BookBoardListServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<BookBoard> bookBoard = bookBoardService.list();
    for (BookBoard book : bookBoard) {
      out.printf("%d, %s, %d점, %s, %d\n", //
          book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(), book.getBookStatus());
    }
  }
}
