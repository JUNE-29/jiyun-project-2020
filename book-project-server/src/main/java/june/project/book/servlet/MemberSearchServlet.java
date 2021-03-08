package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class MemberSearchServlet {

  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/search")
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");

    List<Member> members = memberService.getKeyword(keyword);

    for (Member m : members) {
      out.printf("%d, %s, %s, %s\n", //
          m.getNo(), m.getName(), m.getEmail(), m.getRegisteredDate());
    }
  }
}
