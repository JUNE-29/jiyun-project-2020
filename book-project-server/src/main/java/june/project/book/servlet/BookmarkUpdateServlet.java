package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkUpdateServlet {

  BookmarkService bookmarkService;

  public BookmarkUpdateServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    Bookmark bookmark = new Bookmark();

    bookmark.setNo(Integer.parseInt(params.get("no")));
    bookmark.setTitle(params.get("title"));
    bookmark.setBookTitle(params.get("bookTitle"));
    bookmark.setAuthor(params.get("author"));
    bookmark.setPublisher(params.get("publisher"));
    bookmark.setContent(params.get("content"));
    bookmark.setPhoto(params.get("photo"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/bookmark/list'>");
    out.println("<title>북마크 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>북마크 변경 결과</h1>");

    if (bookmarkService.update(bookmark) > 0) {
      out.println("<p>강의를 변경했습니다.</p>");

    } else {
      out.println("<p>변경에 실패했습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
