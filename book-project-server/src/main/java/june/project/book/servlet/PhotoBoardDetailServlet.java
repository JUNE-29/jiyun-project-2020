package june.project.book.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
@MultipartConfig(maxFileSize = 500000)
public class PhotoBoardDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int bookmarkNo = 0;
    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardService.get(no);

      request.setAttribute("photoBoard", photoBoard);
      request.setAttribute("viewUrl", "/photoboard/detail.jsp");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?bookmarkNo=" + bookmarkNo);
    }
  }
}
