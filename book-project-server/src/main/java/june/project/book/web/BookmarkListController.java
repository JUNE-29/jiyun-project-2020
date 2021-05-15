package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkListController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", bookmarkService.list());
    return "/bookmark/list.jsp";
  }
}
