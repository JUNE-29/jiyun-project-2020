package june.project.book.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardAddServlet {

  PhotoBoardService photoBoardService;
  BookmarkService bookmarkService;


  public PhotoBoardAddServlet(PhotoBoardService photoBoardService,
      BookmarkService bookmarkService) {
    this.photoBoardService = photoBoardService;
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/photoboard/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int bookmarkNo = Integer.parseInt(params.get("bookmarkNo"));
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh'" //
        + " content='2;url=/photoboard/list?bookmarkNo=" + bookmarkNo + "'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 입력 결과</h1>");

    try {
      Bookmark bookmark = bookmarkService.get(bookmarkNo);
      if (bookmark == null) {
        throw new Exception("북마크 번호가 유효하지 않습니다.");
      }

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(params.get("title"));
      photoBoard.setBookmark(bookmark);

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = params.get("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilePath(filepath));
        }
      }

      if (photoFiles.size() == 0) {
        throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
      }

      photoBoard.setFiles(photoFiles);
      photoBoardService.add(photoBoard);

      out.println("<p>새 사진 게시글을 등록했습니다.</p>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
