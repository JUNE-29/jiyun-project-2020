package june.project.book.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/detail")
public class BookBoardDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      ServletContext servletContext = getServletContext();

      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      BookBoard bookBoard = bookBoardService.get(no);

      // JSP가 출력할 때 사용할 수 있도록
      // 조회 결과를 ServletRequest 보관소에 담는다.
      request.setAttribute("bookBoard", bookBoard);

      // 출력을 담당할 JSP를 인클루딩 한다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/bookboard/detail.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
