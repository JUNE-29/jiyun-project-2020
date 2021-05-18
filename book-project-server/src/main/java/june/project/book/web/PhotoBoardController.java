package june.project.book.web;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;

@Controller
@RequestMapping("/photoboard")
public class PhotoBoardController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  BookmarkService bookmarkService;

  @GetMapping("form")
  public void form(int bookmarkNo, Model model) throws Exception {
    model.addAttribute("bookmark", bookmarkService.get(bookmarkNo));
  }

  @PostMapping("add")
  public String add(int bookmarkNo, String title, MultipartFile[] photoFiles) throws Exception {
    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("북마크 번호가 유효하지 않습니다.");
    }

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(title);
    photoBoard.setBookmark(bookmark);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = servletContext.getRealPath("/upload/photoboard");
    for (MultipartFile photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
      photos.add(new PhotoFile().setFilePath(filename));
    }

    if (photos.size() == 0) {
      throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
    }

    photoBoard.setFiles(photos);
    photoBoardService.add(photoBoard);

    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }

  @GetMapping("list")
  public void list(int bookmarkNo, Model model) throws Exception {

    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }
    model.addAttribute("bookmark", bookmark);
    model.addAttribute("list", photoBoardService.listBookmarkPhoto(bookmarkNo));
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    model.addAttribute("photoBoard", photoBoardService.get(no));
  }

  @PostMapping("update")
  public String update(int no, String title, MultipartFile[] photoFiles) throws Exception {

    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(title);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = servletContext.getRealPath("/upload/photoboard");
    for (MultipartFile photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
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

  @GetMapping("delete")
  public String delete(int no, int bookmarkNo) throws Exception {
    photoBoardService.delete(no);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }
}
