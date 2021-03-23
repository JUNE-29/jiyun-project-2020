package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardUpdateServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardSerivce) {
    this.photoBoardService = photoBoardSerivce;
  }

  @RequestMapping("/photoboard/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int no = Integer.parseInt(params.get("no"));

    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(params.get("title"));

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
      String filepath = params.get("photo" + i);
      if (filepath.length() > 0) {
        photoFiles.add(new PhotoFile().setFilePath(filepath));
      }
    }

    if (photoFiles.size() > 0) {
      photoBoard.setFiles(photoFiles);
    } else {
      photoBoard.setFiles(null);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='2;url=/photoboard/list?bookmarkNo=%d'>", //
        photoBoard.getBookmark().getNo());
    out.println("<title>사진 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 변경 결과</h1>");

    try {
      photoBoardService.update(photoBoard);
      out.println("<p>사진을 변경했습니다.</p>");
    } catch (Exception e) {
      out.println("<p>해당 사진 게시물이 존재하지 않습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
