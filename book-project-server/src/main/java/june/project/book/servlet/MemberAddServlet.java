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
import june.project.book.domain.Member;
import june.project.book.service.MemberService;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
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
      out.println("<title>회원 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.println("이름: <input name='name' type='text'><br>");
      out.println("이메일: <input name='email' type='email'><br>");
      out.println("비밀번호: <input name='password' type='password'><br>");
      out.println("사진: <input name='photo' type='text'><br>");
      out.println("<button>제출</button>");
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
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      Member member = new Member();
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));

      memberService.add(member);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>회원 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원 입력 결과</h1>");
      out.println("<p>새 회원을 등록했습니다.</p>");
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
