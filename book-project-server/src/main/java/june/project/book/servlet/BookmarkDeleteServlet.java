package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkDeleteServlet {

  BookmarkService bookmarkService;

  public BookmarkDeleteServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/delete")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/bookmark/list'>");
    out.println("<title>북마크 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>북마크 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));
    if (bookmarkService.delete(no) > 0) {
      out.println("<p>북마크를 삭제했습니다.</p>");

    } else {
      out.println("<p>해당 번호의 북마크가 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
