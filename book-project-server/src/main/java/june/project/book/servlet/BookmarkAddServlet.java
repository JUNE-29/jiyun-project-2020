package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

@WebServlet("/bookmark/add")
public class BookmarkAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>북마크 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>북마크 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.println("제목: <input name='title' type='text'><br>");
      out.println("도서명: <input name='bookTitle' type='text' ><br>");
      out.println("지은이: <input name='author' type='text' ><br>");
      out.println("출판사: <input name='publisher' type='text' ><br>");
      out.println("내용:<br>");
      out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
      out.println("이미지: <input name='photo' type='file' ><br>");
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

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
      bookmark.setTitle(request.getParameter("title"));
      bookmark.setBookTitle(request.getParameter("bookTitle"));
      bookmark.setAuthor(request.getParameter("author"));
      bookmark.setPublisher(request.getParameter("publisher"));
      bookmark.setContent(request.getParameter("content"));
      bookmark.setPhoto(request.getParameter("photo"));

      if (bookmarkService.add(bookmark) > 0) {
        response.sendRedirect("list");
      } else {
        throw new Exception("추가할 수 없습니다.");
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
