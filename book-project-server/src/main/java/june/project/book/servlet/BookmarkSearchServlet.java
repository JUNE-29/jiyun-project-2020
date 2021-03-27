package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@WebServlet("/bookmark/search")
public class BookmarkSearchServlet extends HttpServlet {
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
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      HashMap<String, Object> map = new HashMap<>();

      String value = request.getParameter("title");
      if (value.length() > 0) {
        map.put("title", value);
      }

      value = request.getParameter("bookTitle");
      if (value.length() > 0) {
        map.put("bookTitle", value);
      }

      value = request.getParameter("author");
      if (value.length() > 0) {
        map.put("author", value);
      }

      value = request.getParameter("date");
      if (value.length() > 0) {
        map.put("date", value);
      }

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>북마크 검색</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1>북마크 검색 결과</h1>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>제목</th>");
      out.println("    <th>도서명</th>");
      out.println("    <th>지은이</th>");
      out.println("    <th>등록일</th>");
      out.println("  </tr>");

      List<Bookmark> bookmarks = bookmarkService.search(map);
      for (Bookmark b : bookmarks) {
        out.printf("  <tr>" //
            + "<td>%d</td>" //
            + "<td><a href='detail?no=%d'>%s</a></td>" //
            + "<td>%s</td>" //
            + "<td>%s</td>" //
            + "<td>%s</td>" //
            + "  </tr>\n", //
            b.getNo(), b.getNo(), b.getTitle(), b.getBookTitle(), b.getAuthor(), b.getDate());
      }
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}

