package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberDetailServlet {

  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    int no = Integer.parseInt(params.get("no"));

    Member member = memberService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 상세정보</h1>");

    if (member != null) {
      out.println("<form action='/member/update'>");
      out.printf("번호: <input name='no' type='text' readonly value='%d'><br>\n", member.getNo());
      out.printf("이름: <input name='name' type='text' value='%s'><br>\n", member.getName());
      out.printf("이메일: <input name='email' type='email' value='%s'><br>\n", member.getEmail());
      out.printf("비밀번호: <input name='password' type='password'><br>\n", member.getPassword());
      out.printf("사진: <input name='photo' type='text' value='%s'><br>\n", member.getPhoto());
      out.printf("가입일: <input name='date' type='date' readonly value='%s'><br>\n",
          member.getRegisteredDate());
      out.println("<p><button>변경</button>");
      out.printf("<a href='/member/delete?no=%d'>삭제</a></p>\n", //
          member.getNo());
      out.println("</form>");
    } else {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
