package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Component;
import june.project.util.Prompt;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {

  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Member member = memberService.get(no);

    if (member != null) {
      out.printf("번호: %d \n", member.getNo());
      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("암호: %s\n", member.getPassword());
      out.printf("사진: %s\n", member.getPhoto());
      out.printf("가입일: %s \n", member.getRegisteredDate());

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
