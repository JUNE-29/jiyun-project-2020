package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkUpdateController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Bookmark bookmark = new Bookmark();
    bookmark.setNo(Integer.parseInt(request.getParameter("no")));
    bookmark.setTitle(request.getParameter("title"));
    bookmark.setBookTitle(request.getParameter("bookTitle"));
    bookmark.setAuthor(request.getParameter("author"));
    bookmark.setPublisher(request.getParameter("publisher"));
    bookmark.setContent(request.getParameter("content"));
    bookmark.setPhoto(request.getParameter("photo"));

    if (bookmarkService.update(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
