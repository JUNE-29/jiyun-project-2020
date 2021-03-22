package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.util.RequestMapping;

@Component
public class BookmarkAddFormServlet {

  @RequestMapping("/bookmark/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

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
