package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// @Component // SpringIoC 가 관리
@WebServlet("/book/addForm") // 서블릿 컨테이너가 이 객체를 관리한다.
// => @WebServlet(서블릿경로)
// => 서블릿 컨테이너는 이 애노테이션이 붙은 객체를 생성하여 보관한다.
// => 클라이언트가 '서블릿경로'에 해당하는 URL로 요청하면,
// 서블릿 컨테이너는 그 서블릿경로로 저장된 객체를 실행한다.
//
// JavaEE Servlet 기술에 따라 클라이언트 요청을 처리하는 객체를 만드는 방법:
// => javax.servlet.Servlet 인터페이스를 구현한다.
// => 또는 이 인터페이스를 미리 구현한 javax.servlet.GenericServlet을 상속 받는다.
//
public class BookBoardAddFormServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

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
