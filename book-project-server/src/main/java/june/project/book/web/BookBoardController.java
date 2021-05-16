package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardController {

  @Autowired
  BookBoardService bookBoardService;

  @RequestMapping("/book/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/bookboard/form.jsp";
    }

    BookBoard bookBoard = new BookBoard();
    bookBoard.setBookTitle(request.getParameter("bookTitle"));
    bookBoard.setAuthor(request.getParameter("author"));
    bookBoard.setPublisher(request.getParameter("publisher"));
    bookBoard.setCategories(request.getParameter("categories"));
    bookBoard.setPublishedDate(request.getParameter("publishedDate"));
    bookBoard.setContent(request.getParameter("content"));
    bookBoard.setPhoto(request.getParameter("photo"));
    bookBoard.setBookStatus(Integer.parseInt(request.getParameter("bookStatus")));
    bookBoard.setScore(Integer.parseInt(request.getParameter("score")));
    bookBoardService.add(bookBoard);

    return "redirect:list";
  }

  @RequestMapping("/book/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", bookBoardService.list());
    return "/bookboard/list.jsp";
  }

  @RequestMapping("/book/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    BookBoard bookBoard = bookBoardService.get(no);
    request.setAttribute("bookBoard", bookBoard);
    return "/bookboard/detail.jsp";
  }

  @RequestMapping("/book/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {

      int no = Integer.parseInt(request.getParameter("no"));
      BookBoard bookBoard = bookBoardService.get(no);
      request.setAttribute("bookBoard", bookBoard);
      return "/bookboard/updateform.jsp";
    }

    BookBoard bookBoard = new BookBoard();
    bookBoard.setNo(Integer.parseInt(request.getParameter("no")));
    bookBoard.setBookTitle(request.getParameter("bookTitle"));
    bookBoard.setAuthor(request.getParameter("author"));
    bookBoard.setPublisher(request.getParameter("publisher"));
    bookBoard.setCategories(request.getParameter("categories"));
    bookBoard.setPublishedDate(request.getParameter("publishedDate"));
    bookBoard.setContent(request.getParameter("content"));
    bookBoard.setPhoto(request.getParameter("photo"));
    bookBoard.setScore(Integer.parseInt(request.getParameter("score")));
    bookBoard.setBookStatus(Integer.parseInt(request.getParameter("bookStatus")));

    if (bookBoardService.update(bookBoard) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

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
