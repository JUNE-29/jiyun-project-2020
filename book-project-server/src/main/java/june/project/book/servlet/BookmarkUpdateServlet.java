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
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@WebServlet("/bookmark/update")
public class BookmarkUpdateServlet extends GenericServlet {
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

      Bookmark bookmark = new Bookmark();

      bookmark.setNo(Integer.parseInt(req.getParameter("no")));
      bookmark.setTitle(req.getParameter("title"));
      bookmark.setBookTitle(req.getParameter("bookTitle"));
      bookmark.setAuthor(req.getParameter("author"));
      bookmark.setPublisher(req.getParameter("publisher"));
      bookmark.setContent(req.getParameter("content"));
      bookmark.setPhoto(req.getParameter("photo"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=/bookmark/list'>");
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
