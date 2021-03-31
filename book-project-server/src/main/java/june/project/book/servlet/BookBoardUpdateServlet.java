package june.project.book.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/update")
public class BookBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      request.setCharacterEncoding("UTF-8");

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

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
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "해당 번호의 게시물이 없습니다.");
        request.getSession().setAttribute("url", "bookboard/list");
        response.sendRedirect("../error");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
