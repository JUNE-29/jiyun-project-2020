package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.util.RequestMapping;

@Component
public class BookBoardAddFormServlet {

  @RequestMapping("/book/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>Book 게시글 입력 </title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1> 게시물 입력 </h1>");
    out.println("<form action='/book/add'>");
    out.println("도서명: <input name='bookTitle' type='text' ><br>");
    out.println("지은이: <input name='author' type='text' ><br>");
    out.println("출판사: <input name='publisher' type='text' ><br>");
    out.println("카테고리: <input name='categories' type='text' ><br>");
    out.println("출판 연도: <input name='publishedDate' type='text' ><br>");
    out.println("내용(메모): <textarea name='content' rows='5' cols='60'></textarea><br>");
    out.println("이미지: <input name='photo' type='text' ><br>");
    out.println("진행 상태(1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): ");
    out.println("<input name='bookStatus' type='number'><br>");
    out.println("책에 대한 점수(5점만점): ");
    out.println("<input name='score' type='number'><br>");
    out.println("<button>완료</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
