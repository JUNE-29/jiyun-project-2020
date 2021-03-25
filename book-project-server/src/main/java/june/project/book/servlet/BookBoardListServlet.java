package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/list")
public class BookBoardListServlet extends GenericServlet {
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

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>Book 게시글 목록 </title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1> 게시글 </h1>");
      out.println("  <a href='/book/addForm'> 새 글</a><br>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>도서명</th>");
      out.println("    <th>책에 대한 점수</th>");
      out.println("    <th>등록일</th>");
      out.println("    <th>상태</th>");
      out.println("  </tr>");

      List<BookBoard> bookBoard = bookBoardService.list();
      for (BookBoard book : bookBoard) {
        out.printf("  <tr>" //
            + "<td>%d</td>" //
            + "<td><a href='/book/detail?no=%d'>%s</a></td>" //
            + "<td>%d</td>" //
            + "<td>%s</td>" //
            + "<td>%d</td>" //
            + "</tr>\n", //
            book.getNo(), book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(),
            book.getBookStatus());
      }
      out.println("</table>");

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
