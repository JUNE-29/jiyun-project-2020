package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/update")
public class BookBoardUpdateServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      BookBoard bookBoard = new BookBoard();

      bookBoard.setNo(Integer.parseInt(req.getParameter("no")));
      bookBoard.setBookTitle(req.getParameter("bookTitle"));
      bookBoard.setAuthor(req.getParameter("author"));
      bookBoard.setPublisher(req.getParameter("publisher"));
      bookBoard.setCategories(req.getParameter("categories"));
      bookBoard.setPublishedDate(req.getParameter("publishedDate"));
      bookBoard.setContent(req.getParameter("content"));
      bookBoard.setPhoto(req.getParameter("photo"));
      bookBoard.setScore(Integer.parseInt(req.getParameter("score")));
      bookBoard.setBookStatus(Integer.parseInt(req.getParameter("bookStatus")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=/book/list'>");
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
