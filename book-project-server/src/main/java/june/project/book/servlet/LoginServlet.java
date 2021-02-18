package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Prompt;

public class LoginServlet implements Servlet {

  MemberService memberService;

  public LoginServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String email = Prompt.getString(in, out, "이메일? ");
    String password = Prompt.getString(in, out, "암호? ");

    Member member = memberService.findByEmailAndPassword(email, password);

    if (member != null) {
      out.printf("'%s'님 환영합니다.\n", member.getName());
    } else {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}
