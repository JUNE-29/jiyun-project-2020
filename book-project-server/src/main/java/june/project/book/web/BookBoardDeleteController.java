package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardDeleteController {

  @Autowired
  BookBoardService bookBoardService;

  @RequestMapping("/book/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    if (bookBoardService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
