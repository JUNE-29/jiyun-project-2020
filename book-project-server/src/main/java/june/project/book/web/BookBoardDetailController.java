package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardDetailController {

  @Autowired
  BookBoardService bookBoardService;

  @RequestMapping("/book/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    BookBoard bookBoard = bookBoardService.get(no);

    request.setAttribute("bookBoard", bookBoard);
    return "/bookboard/detail.jsp";
  }
}
