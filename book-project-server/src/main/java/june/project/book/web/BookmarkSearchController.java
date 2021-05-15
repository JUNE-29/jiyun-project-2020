package june.project.book.servlet;

import java.io.IOException;
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
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

      List<Bookmark> bookmarks = bookmarkService.search(map);
      request.setAttribute("list", bookmarks);
      request.setAttribute("viewUrl", "/bookmark/search.jsp");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
    }
  }
}

