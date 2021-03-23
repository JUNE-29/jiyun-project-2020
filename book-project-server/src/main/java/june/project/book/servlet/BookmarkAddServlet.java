package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkAddServlet {

  BookmarkService bookmarkService;

  public BookmarkAddServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    Bookmark bookmark = new Bookmark();
    bookmark.setTitle(params.get("title"));
    bookmark.setBookTitle(params.get("bookTitle"));
    bookmark.setAuthor(params.get("author"));
    bookmark.setPublisher(params.get("publisher"));
    bookmark.setContent(params.get("content"));
    bookmark.setPhoto(params.get("photo"));

    bookmarkService.add(bookmark);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/bookmark/list'>");
    out.println("<title>북마크 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>북마크 입력 결과</h1>");
    out.println("<p>새 북마크를 등록했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
