package june.project.book.servlet;

import java.io.IOException;
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
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

      if (bookmarkService.update(bookmark) > 0) {
        request.setAttribute("viewUrl", "redirect:list");
      } else {
        throw new Exception("번호가 유효하지 않습니다.");
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
    }
  }
}
