package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.BookmarkService;
import june.project.util.RequestMapping;

@Component
public class BookmarkDeleteController {

  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/bookmark/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    if (bookmarkService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
