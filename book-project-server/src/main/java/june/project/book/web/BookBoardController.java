package june.project.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBasketService;
import june.project.book.service.BookcaseService;

@Controller
@RequestMapping("/bookboard")
public class BookBoardController {

  @Autowired
  BookBasketService bookBasketService;

  @Autowired
  BookcaseService bookcaseService;

  @GetMapping("form")
  public void form() throws Exception {}

  @PostMapping("add")
  public String add(BookBoard bookBoard) throws Exception {
   // bookBoardService.add(bookBoard);
    return "redirect:list";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("bookBasketList", bookBasketService.list());
    model.addAttribute("bookcaseList", bookcaseService.list());
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
//    BookBoard bookBoard = bookBoardService.get(no);
//    model.addAttribute("bookBoard", bookBoard);
  }

//  @PostMapping("update")
//  public String update(BookBoard bookBoard) throws Exception {
//      if (bookBoardService.update(bookBoard) > 0) {
//     return "redirect:list";
//   } else {
//     throw new Exception("번호가 유효하지 않습니다.");
//   }
//  }

//  @GetMapping("delete")
//  public String delete(int no) throws Exception {
//    if (bookBoardService.delete(no) > 0) {
//      return "redirect:list";
//    } else {
//      throw new Exception("번호가 유효하지 않습니다.");
//   }
//  }
}
