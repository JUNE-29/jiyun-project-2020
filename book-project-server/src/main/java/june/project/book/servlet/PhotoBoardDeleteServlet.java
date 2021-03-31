package june.project.book.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
    int no = Integer.parseInt(request.getParameter("no"));

    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      photoBoardService.delete(no);
      response.sendRedirect("list?bookmarkNo=" + bookmarkNo);

    } catch (Exception e) {
      request.getSession().setAttribute("errorMessage", e.getMessage());
      request.getSession().setAttribute("url", "photoboard/list?bookmarkNo=" + bookmarkNo);
      response.sendRedirect("../error");
    }
  }
}
