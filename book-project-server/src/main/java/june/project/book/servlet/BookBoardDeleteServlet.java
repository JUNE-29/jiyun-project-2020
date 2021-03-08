package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.service.BookBoardService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class BookBoardDeleteServlet {

  BookBoardService bookBoardService;

  public BookBoardDeleteServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/delete")
  public void service(Scanner in, PrintStream out) throws Exception {


    int no = Prompt.getInt(in, out, "번호? ");

    if (bookBoardService.delete(no) > 0) {
      out.println("삭제했습니다.");

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
