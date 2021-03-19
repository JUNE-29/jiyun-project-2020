package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardDeleteServlet {

  BookBoardService bookBoardService;

  public BookBoardDeleteServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/delete")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/book/list'>");
    out.println("<title>Book 게시글 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Book 게시글 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));
    if (bookBoardService.delete(no) > 0) {
      out.println("<p>Book 게시글을 삭제했습니다.</p>");

    } else {
      out.println("<p>해당 번호의 강의가 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
