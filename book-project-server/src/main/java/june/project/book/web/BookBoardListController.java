package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardListController {

  @Autowired
  BookBoardService bookBoardService;

  @RequestMapping("/book/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setAttribute("list", bookBoardService.list());
    return "/bookboard/list.jsp";
  }
}
