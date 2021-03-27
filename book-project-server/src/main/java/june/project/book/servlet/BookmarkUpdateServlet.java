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
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@WebServlet("/bookmark/update")
public class BookmarkUpdateServlet extends HttpServlet {
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
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      Bookmark bookmark = new Bookmark();

      bookmark.setNo(Integer.parseInt(request.getParameter("no")));
      bookmark.setTitle(request.getParameter("title"));
      bookmark.setBookTitle(request.getParameter("bookTitle"));
      bookmark.setAuthor(request.getParameter("author"));
      bookmark.setPublisher(request.getParameter("publisher"));
      bookmark.setContent(request.getParameter("content"));
      bookmark.setPhoto(request.getParameter("photo"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>북마크 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>북마크 변경 결과</h1>");

      if (bookmarkService.update(bookmark) > 0) {
        out.println("<p>강의를 변경했습니다.</p>");

      } else {
        out.println("<p>변경에 실패했습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
