package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class MemberAddServlet {

  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/add")
  public void service(Scanner in, PrintStream out) throws Exception {

    Member member = new Member();

    member.setName(Prompt.getString(in, out, "이름? "));
    member.setEmail(Prompt.getString(in, out, "이메일? "));
    member.setPassword(Prompt.getString(in, out, "비밀번호? "));
    member.setPhoto(Prompt.getString(in, out, "사진? "));

    if (memberService.add(member) > 0) {
      out.println("등록했습니다.");

    } else {
      out.println("회원 등록에 실패했습니다.");
    }
  }
}
