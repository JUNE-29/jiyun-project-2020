package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.service.BookBoardService;
import june.project.util.Component;
import june.project.util.Prompt;

@Component("/book/delete")
public class BookBoardDeleteServlet implements Servlet {

  BookBoardService bookBoardService;

  public BookBoardDeleteServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {


    int no = Prompt.getInt(in, out, "번호? ");

    if (bookBoardService.delete(no) > 0) {
      out.println("삭제했습니다.");

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
