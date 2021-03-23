package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkDetailServlet {

  BookmarkService bookmarkService;

  public BookmarkDetailServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/detail")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int no = Integer.parseInt(params.get("no"));
    Bookmark bookmark = bookmarkService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>북마크 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>북마크 상세정보</h1>");

    if (bookmark != null) {
      out.println("<form action='/bookmark/update'>");
      out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", //
          bookmark.getNo());
      out.printf("제목: <input name='title' type='text' value='%s'><br>\n", //
          bookmark.getTitle());
      out.printf("도서명: <input name='bookTitle' type='text' value='%s'><br>\n", //
          bookmark.getBookTitle());
      out.printf("지은이: <input name='author' type='text' value='%s'><br>\n", //
          bookmark.getAuthor());
      out.printf("출판사: <input name='publisher' type='text' value='%s'><br>\n", //
          bookmark.getPublisher());
      out.printf("필사 내용: <textarea name='content' rows='5' cols='60'>%s</textarea><br>\n", //
          bookmark.getContent());
      out.printf("이미지: <input name='photo' type='text' value='%s'><br>\n", //
          bookmark.getPhoto());
      out.printf("등록일: %s\n", bookmark.getDate());
      out.println("<p>");
      out.println("<button>변경</button>");
      out.printf("<a href='/bookmark/delete?no=%d'>삭제</a>\n", bookmark.getNo());
      out.printf("<a href='/photoboard/list?bookmarkNo=%d'>사진게시판</a>\n", bookmark.getNo());
      out.println("</p>");
      out.println("</form>");
    } else {
      out.println("<p>해당 번호의 강의가 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
