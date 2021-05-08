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
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/list")
public class BookBoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      List<BookBoard> bookBoard = bookBoardService.list();

      // JSP에게 출력을 위임하기 전에
      // JSP가 사용할 데이터를 ServletRequest에 보관한다.
      request.setAttribute("list", bookBoard);

      // JSP를 인클루드하여 출력을 맡긴다.
      // => 인클루드 하는 쪽에서 출력 스트림의 콘텐트타입을 설정해야 한다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/bookboard/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
