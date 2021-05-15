package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkAddController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/bookmark/form.jsp";
    }

    Bookmark bookmark = new Bookmark();
    bookmark.setTitle(request.getParameter("title"));
    bookmark.setBookTitle(request.getParameter("bookTitle"));
    bookmark.setAuthor(request.getParameter("author"));
    bookmark.setPublisher(request.getParameter("publisher"));
    bookmark.setContent(request.getParameter("content"));
    bookmark.setPhoto(request.getParameter("photo"));

    if (bookmarkService.add(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("추가할 수 없습니다.");
    }
  }
}
