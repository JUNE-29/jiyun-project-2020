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
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      request.setCharacterEncoding("UTF-8");

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
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "번호가 유효하지 않습니다.");
        request.getSession().setAttribute("url", "bookmark/list");
        response.sendRedirect("../error");
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
