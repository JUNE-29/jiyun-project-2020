package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkSearchServlet {

  BookmarkService bookmarkService;

  public BookmarkSearchServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/search")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    HashMap<String, Object> map = new HashMap<>();

    String value = params.get("title");
    if (value.length() > 0) {
      map.put("title", value);
    }

    value = params.get("bookTitle");
    if (value.length() > 0) {
      map.put("bookTitle", value);
    }

    value = params.get("author");
    if (value.length() > 0) {
      map.put("author", value);
    }

    value = params.get("date");
    if (value.length() > 0) {
      map.put("date", value);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>북마크 검색</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>북마크 검색 결과</h1>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>도서명</th>");
    out.println("    <th>지은이</th>");
    out.println("    <th>등록일</th>");
    out.println("  </tr>");

    List<Bookmark> bookmarks = bookmarkService.search(map);
    for (Bookmark b : bookmarks) {
      out.printf("  <tr>" //
          + "<td>%d</td>" //
          + "<td><a href='/bookmark/detail?no=%d'>%s</a></td>" //
          + "<td>%s</td>" //
          + "<td>%s</td>" //
          + "<td>%s</td>" //
          + "  </tr>\n", //
          b.getNo(), b.getNo(), b.getTitle(), b.getBookTitle(), b.getAuthor(), b.getDate());
    }
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}

