package june.project.book.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class LoginServlet {

  MemberService memberService;

  public LoginServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/auth/login")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    String email = params.get("email");
    String password = params.get("password");

    Member member = memberService.get(email, password);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    if (member != null) {
      out.println("<meta http-equiv='refresh' content='2;url=/book/list'>");
    } else {
      out.println("<meta http-equiv='refresh' content='2;url=/auth/loginForm'>");
    }
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인 결과</h1>");

    if (member != null) {
      out.printf("<p>'%s'님 환영합니다.</p>\n", member.getName());
    } else {
      out.println("<p>사용자 정보가 유효하지 않습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
