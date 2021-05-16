package june.project.book.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardController {

  @Autowired
  BookBoardService bookBoardService;

  @RequestMapping("/book/form")
  public String form() throws Exception {
    return "/bookboard/form.jsp";
  }

  @RequestMapping("/book/add")
  public String add(BookBoard bookBoard) throws Exception {
    bookBoardService.add(bookBoard);
    return "redirect:list";
  }

  @RequestMapping("/book/list")
  public String list(Map<String, Object> model) throws Exception {
    model.put("list", bookBoardService.list());
    return "/bookboard/list.jsp";
  }

  @RequestMapping("/book/detail")
  public String detail(int no, Map<String, Object> model) throws Exception {
    BookBoard bookBoard = bookBoardService.get(no);
    model.put("bookBoard", bookBoard);
    return "/bookboard/detail.jsp";
  }

  @RequestMapping("/book/update")
  public String update(BookBoard bookBoard) throws Exception {
    if (bookBoardService.update(bookBoard) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @RequestMapping("/book/delete")
  public String delete(int no) throws Exception {
    if (bookBoardService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
