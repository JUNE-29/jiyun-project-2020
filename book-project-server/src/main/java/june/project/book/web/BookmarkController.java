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
public class BookmarkController {

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

  @RequestMapping("/bookmark/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", bookmarkService.list());
    return "/bookmark/list.jsp";
  }

  @RequestMapping("/bookmark/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Bookmark bookmark = bookmarkService.get(no);
    request.setAttribute("bookmark", bookmark);
    return "/bookmark/detail.jsp";
  }

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

  @RequestMapping("/bookmark/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    if (bookmarkService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

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
