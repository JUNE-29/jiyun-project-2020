package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardAddController {

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
}
