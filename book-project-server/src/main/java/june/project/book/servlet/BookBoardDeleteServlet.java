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
import june.project.book.service.BookBoardService;

@WebServlet("/book/delete")
public class BookBoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>Book 게시글 삭제</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Book 게시물 삭제 결과</h1>");

      int no = Integer.parseInt(request.getParameter("no"));
      if (bookBoardService.delete(no) > 0) {
        out.println("<p>Book 게시물을 삭제했습니다.</p>");

      } else {
        out.println("<p>해당 번호의 게시물이 없습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
