package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardAddFormServlet {

  BookmarkService bookmarkService;

  public PhotoBoardAddFormServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int BookmarkNo = Integer.parseInt(params.get("bookmarkNo"));
    Bookmark bookmark = bookmarkService.get(BookmarkNo);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 입력</h1>");
    out.println("<form action='/photoboard/add'>");
    out.printf("북마크 번호: <input name='bookmarkNo' type='text' value='%d' readonly><br>\n", //
        bookmark.getNo());
    out.printf("북마크 제목: %s<br>\n", bookmark.getTitle());
    out.println("사진 제목:<br>");
    out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
    out.println("<hr>");
    out.println("사진: <input name='photo1' type='file'><br>");
    out.println("사진: <input name='photo2' type='file'><br>");
    out.println("사진: <input name='photo3' type='file'><br>");
    out.println("사진: <input name='photo4' type='file'><br>");
    out.println("사진: <input name='photo5' type='file'><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
