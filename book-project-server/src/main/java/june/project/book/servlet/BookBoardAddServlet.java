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
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@WebServlet("/book/add")
public class BookBoardAddServlet extends HttpServlet {
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
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>Book 게시글 입력 </title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1> 게시물 입력 </h1>");
      out.println("<form action='add' method='post'>");
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
    } catch (Exception e) {
      throw new ServletException(e);
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
      BookBoardService bookBoardService = iocContainer.getBean(BookBoardService.class);

      BookBoard bookBoard = new BookBoard();
      bookBoard.setBookTitle(request.getParameter("bookTitle"));
      bookBoard.setAuthor(request.getParameter("author"));
      bookBoard.setPublisher(request.getParameter("publisher"));
      bookBoard.setCategories(request.getParameter("categories"));
      bookBoard.setPublishedDate(request.getParameter("publishedDate"));
      bookBoard.setContent(request.getParameter("content"));
      bookBoard.setPhoto(request.getParameter("photo"));
      bookBoard.setBookStatus(Integer.parseInt(request.getParameter("bookStatus")));
      bookBoard.setScore(Integer.parseInt(request.getParameter("score")));

      bookBoardService.add(bookBoard);

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
