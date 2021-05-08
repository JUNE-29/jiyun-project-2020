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

@WebServlet("/book/add")
public class BookBoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/bookboard/form.jsp").include(request, response);
  }

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

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
