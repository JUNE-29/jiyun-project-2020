package june.project.book.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardController {

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/photoboard/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      int BookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
      Bookmark bookmark = bookmarkService.get(BookmarkNo);
      request.setAttribute("bookmark", bookmark);
      return "/photoboard/form.jsp";
    }

    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));

    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("북마크 번호가 유효하지 않습니다.");
    }

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(request.getParameter("title"));
    photoBoard.setBookmark(bookmark);

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    Collection<Part> parts = request.getParts();
    String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
    for (Part part : parts) {
      if (!part.getName().equals("photo") || part.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      photoFiles.add(new PhotoFile().setFilePath(filename));
    }

    if (photoFiles.size() == 0) {
      throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
    }

    photoBoard.setFiles(photoFiles);
    photoBoardService.add(photoBoard);

    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }

  @RequestMapping("/photoboard/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }
    request.setAttribute("bookmark", bookmark);
    List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);
    request.setAttribute("list", photoBoards);
    return "/photoboard/list.jsp";
  }

  @RequestMapping("/photoboard/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    PhotoBoard photoBoard = photoBoardService.get(no);
    request.setAttribute("photoBoard", photoBoard);
    return "/photoboard/detail.jsp";
  }

  @RequestMapping("/photoboard/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    int bookmarkNo = 0;
    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(request.getParameter("title"));

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    Collection<Part> parts = request.getParts();
    String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
    for (Part part : parts) {
      if (!part.getName().equals("photo") || part.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      photoFiles.add(new PhotoFile().setFilePath(filename));
    }

    if (photoFiles.size() > 0) {
      photoBoard.setFiles(photoFiles);
    } else {
      photoBoard.setFiles(null);
    }

    bookmarkNo = photoBoard.getBookmark().getNo();
    photoBoardService.update(photoBoard);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }

  @RequestMapping("/photoboard/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
    int no = Integer.parseInt(request.getParameter("no"));
    photoBoardService.delete(no);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }
}
