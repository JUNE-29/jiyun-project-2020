package june.project.book.web;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

  @Autowired
  BookmarkService bookmarkService;

  @GetMapping("form")
  public void form() {}

  @PostMapping("add")
  public String add(Bookmark bookmark) throws Exception {
    if (bookmarkService.add(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("추가할 수 없습니다.");
    }
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", bookmarkService.list());
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    // model.addAttribute("bookmark", bookmarkService.get(no));
  }

  @PostMapping("update")
  public String update(Bookmark bookmark) throws Exception {
    if (bookmarkService.update(bookmark) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (bookmarkService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @GetMapping("search")
  public void search(Bookmark bookmark, Model model) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    if (bookmark.getTitle().length() > 0) {
      map.put("title", bookmark.getTitle());
    }

    if (bookmark.getTitle().length() > 0) {
      map.put("title", bookmark.getTitle());
    }

    if (bookmark.getContent().length() > 0) {
      map.put("content", bookmark.getContent());
    }

    if (bookmark.getDate() != null) {
      map.put("date", bookmark.getDate().toString());
    }

    model.addAttribute("list", bookmarkService.search(map));
  }
}
