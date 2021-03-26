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
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@WebServlet("/bookmark/list")
public class BookmarkListServlet extends GenericServlet {
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
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>북마크 목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1>북마크</h1>");
      out.println("  <a href='/bookmark/addForm'>북마크 추가</a><br>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>제목</th>");
      out.println("    <th>도서명</th>");
      out.println("    <th>지은이</th>");
      out.println("    <th>등록일</th>");
      out.println("  </tr>");

      List<Bookmark> bookmark = bookmarkService.list();
      for (Bookmark bm : bookmark) {
        out.printf("  <tr>" //
            + "<td>%d</td>" //
            + "<td><a href='/bookmark/detail?no=%d'>%s</a></td>" //
            + "<td>%s</td>" //
            + "<td>%s</td>" //
            + "<td>%s</td>" //
            + "  </tr>", //
            bm.getNo(), bm.getNo(), bm.getTitle(), bm.getBookTitle(), bm.getAuthor(), bm.getDate());
      }
      out.println("</table>");
      out.println("<hr>");

      out.println("<form action='/bookmark/search'>");
      out.println("제목: <input name='title' type='text'><br>");
      out.println("도서명: <input name='bookTitle' type='text'><br>");
      out.println("지은이: <input name='author' type='text'><br>");
      out.println("등록일: <input name='date' type='date'><br>");
      out.println("<button>검색</button>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
