package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      BookmarkService bookmarkService = iocContainer.getBean(BookmarkService.class);

      request.getRequestDispatcher("/header").include(request, response);

      try {
        Bookmark bookmark = bookmarkService.get(bookmarkNo);

        if (bookmark == null) {
          throw new Exception("번호가 유효하지 않습니다.");
        }

        out.printf("  <h1>책과 함께한 사진 - %s</h1>", bookmark.getTitle());

        out.printf("  <a href='add?bookmarkNo=%d'> 추가 </a><br>\n", //
            bookmarkNo);
        out.println("  <table border='1'>");
        out.println("  <tr>");
        out.println("    <th>번호</th>");
        out.println("    <th>제목</th>");
        out.println("    <th>등록일</th>");
        out.println("    <th>조회수</th>");
        out.println("  </tr>");


        List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);
        for (PhotoBoard photoBaord : photoBoards) {
          out.printf("  <tr>"//
              + "<td>%d</td> "//
              + "<td><a href='detail?no=%d'>%s</a></td> "//
              + "<td>%s</td> "//
              + "<td>%d</td>"//
              + "</tr>\n", //
              photoBaord.getNo(), //
              photoBaord.getNo(), //
              photoBaord.getTitle(), //
              photoBaord.getCreadtedDate(), //
              photoBaord.getViewCount());
        }
        out.println("</table>");

      } catch (Exception e) {
        out.printf("<p>%s</p>\n", e.getMessage());
      }
      request.getRequestDispatcher("/footer").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?bookmarkNo=" + bookmarkNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
