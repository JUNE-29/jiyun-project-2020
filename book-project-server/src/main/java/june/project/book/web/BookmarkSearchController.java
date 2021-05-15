package june.project.book.web;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkSearchController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/search")
  public String search(HttpServletRequest request, HttpServletResponse response) throws Exception {

    HashMap<String, Object> map = new HashMap<>();

    String value = request.getParameter("title");
    if (value.length() > 0) {
      map.put("title", value);
    }

    value = request.getParameter("bookTitle");
    if (value.length() > 0) {
      map.put("bookTitle", value);
    }

    value = request.getParameter("author");
    if (value.length() > 0) {
      map.put("author", value);
    }

    value = request.getParameter("date");
    if (value.length() > 0) {
      map.put("date", value);
    }

    List<Bookmark> bookmarks = bookmarkService.search(map);
    request.setAttribute("list", bookmarks);
    return "/bookmark/search.jsp";
  }
}

