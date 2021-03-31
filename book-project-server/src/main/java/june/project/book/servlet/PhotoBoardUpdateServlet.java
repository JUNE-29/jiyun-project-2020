package june.project.book.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.PhotoBoardService;

@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int no = Integer.parseInt(request.getParameter("no"));
    int bookmarkNo = 0;

    try {
      request.setCharacterEncoding("UTF-8");

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);


      PhotoBoard photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = request.getParameter("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilePath(filepath));
        }
      }

      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);
      } else {
        photoBoard.setFiles(null);
      }

      bookmarkNo = photoBoard.getBookmark().getNo();
      photoBoardService.update(photoBoard);
      response.sendRedirect("list?bookmarkNo=" + bookmarkNo);

    } catch (Exception e) {
      request.getSession().setAttribute("errorMessage", e.getMessage());
      request.getSession().setAttribute("url", "photoboard/list?bookmarkNo=" + bookmarkNo);
      response.sendRedirect("../error");
    }
  }
}
