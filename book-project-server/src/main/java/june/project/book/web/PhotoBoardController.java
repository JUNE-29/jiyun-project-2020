package june.project.book.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;

@Controller
public class PhotoBoardController {

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/photoboard/form")
  public String form(int bookmarkNo, Map<String, Object> model) throws Exception {
    model.put("bookmark", bookmarkService.get(bookmarkNo));
    return "/photoboard/form.jsp";
  }

  @RequestMapping("/photoboard/add")
  public String add(int bookmarkNo, String title, Part[] photoFiles, HttpServletRequest request)
      throws Exception {

    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("북마크 번호가 유효하지 않습니다.");
    }

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(title);
    photoBoard.setBookmark(bookmark);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
    for (Part photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.write(dirPath + "/" + filename);
      photos.add(new PhotoFile().setFilePath(filename));
    }

    if (photos.size() == 0) {
      throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
    }

    photoBoard.setFiles(photos);
    photoBoardService.add(photoBoard);

    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }

  @RequestMapping("/photoboard/list")
  public String list(int bookmarkNo, Map<String, Object> model) throws Exception {

    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }
    model.put("bookmark", bookmark);

    List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);
    model.put("list", photoBoards);
    return "/photoboard/list.jsp";
  }

  @RequestMapping("/photoboard/detail")
  public String detail(int no, Map<String, Object> model) throws Exception {
    model.put("photoBoard", photoBoardService.get(no));
    return "/photoboard/detail.jsp";
  }

  @RequestMapping("/photoboard/update")
  public String update(int no, String title, Part[] photoFiles, HttpServletRequest request)
      throws Exception {

    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(title);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
    for (Part photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.write(dirPath + "/" + filename);
      photos.add(new PhotoFile().setFilePath(filename));
    }

    if (photos.size() > 0) {
      photoBoard.setFiles(photos);
    } else {
      photoBoard.setFiles(null);
    }

    int bookmarkNo = photoBoard.getBookmark().getNo();
    photoBoardService.update(photoBoard);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }

  @RequestMapping("/photoboard/delete")
  public String delete(int no, int bookmarkNo) throws Exception {
    photoBoardService.delete(no);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }
}
