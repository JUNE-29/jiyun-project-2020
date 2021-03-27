package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

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

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>Book 게시글 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Book 게시글 변경 결과</h1>");

      if (bookBoardService.update(bookBoard) > 0) {
        out.println("<p>Book 게시글을 변경했습니다.</p>");

      } else {
        out.println("<p>Book 게시글 변경에 실패했습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
