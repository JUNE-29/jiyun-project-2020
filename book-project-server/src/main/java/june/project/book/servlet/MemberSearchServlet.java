package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Prompt;

public class MemberSearchServlet implements Servlet {

  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");

    List<Member> members = memberService.getKeyword(keyword);

    for (Member m : members) {
      out.printf("%d, %s, %s, %s\n", //
          m.getNo(), m.getName(), m.getEmail(), m.getRegisteredDate());
    }
  }
}
