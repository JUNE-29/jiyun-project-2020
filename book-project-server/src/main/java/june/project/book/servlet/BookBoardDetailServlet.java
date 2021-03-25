package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/detail")
public class BookBoardDetailServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      int no = Integer.parseInt(req.getParameter("no"));
      BookBoard bookBoard = bookBoardService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>Book 게시글 상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Book 게시글 상세정보</h1>");

      if (bookBoard != null) {
        out.println("<form action='/book/update'>");
        out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n",
            bookBoard.getNo());
        out.printf("도서명: <input name='bookTitle' type='text' value='%s'><br>\n",
            bookBoard.getBookTitle());
        out.printf("지은이: <input name='author' type='text' value='%s'><br>\n",
            bookBoard.getAuthor());
        out.printf("출판사: <input name='publisher' type='text' value='%s'><br>\n",
            bookBoard.getPublisher());
        out.printf("출판 연도: <input name='publishedDate' type='text' value='%s'><br>\n",
            bookBoard.getPublishedDate());
        out.printf("카테고리: <input name='categories' type='text' value='%s'><br>\n",
            bookBoard.getCategories());
        out.printf("내용: <textarea name='content' rows='5' cols='60'>%s</textarea><br>\n",
            bookBoard.getContent());
        out.printf("이미지: <input name='photo' type='text' value='%s'><br>\n", bookBoard.getPhoto());
        out.printf("평가: <input name='score' type='number' value='%d'>점<br>\n",
            bookBoard.getScore());
        out.printf(
            "진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): <input name='bookStatus' type='number' value='%d'><br>\n",
            bookBoard.getBookStatus());
        out.printf("등록일: %s<br>\n", bookBoard.getDate());
        out.println("<p>");
        out.println("<button>변경</button>");
        out.printf("<a href='/book/delete?no=%d'>삭제</a>\n", bookBoard.getNo());
        out.println("</p>");
        out.println("</form>");
      } else {
        out.println("<p>해당 번호의 게시물이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
