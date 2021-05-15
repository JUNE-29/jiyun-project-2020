package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkDetailController {
  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Bookmark bookmark = bookmarkService.get(no);
    request.setAttribute("bookmark", bookmark);
    return "/bookmark/detail.jsp";
  }
}
