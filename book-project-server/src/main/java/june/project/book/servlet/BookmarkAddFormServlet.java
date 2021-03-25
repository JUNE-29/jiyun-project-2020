package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/bookmark/addForm")
public class BookmarkAddFormServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>북마크 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>북마크 입력</h1>");
    out.println("<form action='/bookmark/add'>");
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
  }
}
