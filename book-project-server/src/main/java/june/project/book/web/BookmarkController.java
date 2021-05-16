package june.project.book.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/form")
  public String form() {
    return "/bookmark/form.jsp";
  }

  @RequestMapping("/bookmark/add")
  public String add(Bookmark bookmark) throws Exception {
    if (bookmarkService.add(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("추가할 수 없습니다.");
    }
  }

  @RequestMapping("/bookmark/list")
  public String list(Map<String, Object> model) throws Exception {
    model.put("list", bookmarkService.list());
    return "/bookmark/list.jsp";
  }

  @RequestMapping("/bookmark/detail")
  public String detail(int no, Map<String, Object> model) throws Exception {
    model.put("bookmark", bookmarkService.get(no));
    return "/bookmark/detail.jsp";
  }

  @RequestMapping("/bookmark/update")
  public String update(Bookmark bookmark) throws Exception {
    if (bookmarkService.update(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @RequestMapping("/bookmark/delete")
  public String delete(int no) throws Exception {
    if (bookmarkService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @RequestMapping("/bookmark/search")
  public String search(Bookmark bookmark, Map<String, Object> model) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    if (bookmark.getTitle().length() > 0) {
      map.put("title", bookmark.getTitle());
    }

    if (bookmark.getBookTitle().length() > 0) {
      map.put("bookTitle", bookmark.getBookTitle());
    }

    if (bookmark.getAuthor().length() > 0) {
      map.put("author", bookmark.getAuthor());
    }

    if (bookmark.getDate() != null) {
      map.put("date", bookmark.getDate().toString());
    }

    model.put("list", bookmarkService.search(map));
    return "/bookmark/search.jsp";
  }
}
