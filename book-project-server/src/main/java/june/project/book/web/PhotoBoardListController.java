package june.project.book.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardListController {

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  BookmarkService bookmarkService;

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
}
