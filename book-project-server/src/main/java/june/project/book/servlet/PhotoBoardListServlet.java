package june.project.book.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      Bookmark bookmark = bookmarkService.get(bookmarkNo);

      if (bookmark == null) {
        throw new Exception("번호가 유효하지 않습니다.");
      }

      request.setAttribute("bookmark", bookmark);

      List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);
      request.setAttribute("list", photoBoards);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/photoboard/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?bookmarkNo=" + bookmarkNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
