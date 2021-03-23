package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PhotoBoardService photoBoardService;
  BookmarkService bookmarkService;


  public PhotoBoardListServlet(PhotoBoardService photoBoardService,
      BookmarkService bookmarkService) {
    this.photoBoardService = photoBoardService;
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/photoboard/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>책과 함께한 사진 목록</title>");
    out.println("</head>");
    out.println("<body>");

    try {
      int bookmarkNo = Integer.parseInt(params.get("bookmarkNo"));
      Bookmark bookmark = bookmarkService.get(bookmarkNo);

      if (bookmark == null) {
        throw new Exception("번호가 유효하지 않습니다.");
      }

      out.printf("  <h1>책과 함께한 사진 - %s</h1>", bookmark.getTitle());

      out.printf("  <a href='/photoboard/addForm?bookmarkNo=%d'> 추가 </a><br>\n", //
          bookmarkNo);
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>제목</th>");
      out.println("    <th>등록일</th>");
      out.println("    <th>조회수</th>");
      out.println("  </tr>");


      List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);
      for (PhotoBoard photoBaord : photoBoards) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td> "//
            + "<td>%s</td> "//
            + "<td>%d</td>"//
            + "</tr>\n", //
            photoBaord.getNo(), //
            photoBaord.getNo(), //
            photoBaord.getTitle(), //
            photoBaord.getCreadtedDate(), //
            photoBaord.getViewCount());
      }
      out.println("</table>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
