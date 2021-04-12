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

@WebServlet("/bookmark/detail")
public class BookmarkDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      Bookmark bookmark = bookmarkService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>북마크 상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>북마크 상세정보</h1>");

      if (bookmark != null) {
        out.println("<form action='update' method='post'>");
        out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", //
            bookmark.getNo());
        out.printf("제목: <input name='title' type='text' value='%s'><br>\n", //
            bookmark.getTitle());
        out.printf("도서명: <input name='bookTitle' type='text' value='%s'><br>\n", //
            bookmark.getBookTitle());
        out.printf("지은이: <input name='author' type='text' value='%s'><br>\n", //
            bookmark.getAuthor());
        out.printf("출판사: <input name='publisher' type='text' value='%s'><br>\n", //
            bookmark.getPublisher());
        out.printf("필사 내용: <textarea name='content' rows='5' cols='60'>%s</textarea><br>\n", //
            bookmark.getContent());
        out.printf("이미지: <input name='photo' type='text' value='%s'><br>\n", //
            bookmark.getPhoto());
        out.printf("등록일: %s\n", bookmark.getDate());
        out.println("<p>");
        out.println("<button>변경</button>");
        out.printf("<a href='delete?no=%d'>삭제</a>\n", bookmark.getNo());
        out.printf("<a href='../photoboard/list?bookmarkNo=%d'>사진게시판</a>\n", bookmark.getNo());
        out.println("</p>");
        out.println("</form>");
      } else {
        out.println("<p>해당 번호의 강의가 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
