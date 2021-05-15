package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardUpdateController {

  @Autowired
  BookBoardService bookBoardService;

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
}
