package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Component;

@Component("/member/list")
public class MemberListServlet implements Servlet {

  MemberService memberService;

  public MemberListServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Member> member = memberService.list();
    for (Member m : member) {
      out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(), m.getRegisteredDate());
    }
  }
}
