package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardListServlet {

  BookBoardService bookBoardService;

  public BookBoardListServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/list")
  public void service(Scanner in, PrintStream out) throws Exception {

    List<BookBoard> bookBoard = bookBoardService.list();
    for (BookBoard book : bookBoard) {
      out.printf("%d, %s, %d점, %s, %d\n", //
          book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(), book.getBookStatus());
    }
  }
}
