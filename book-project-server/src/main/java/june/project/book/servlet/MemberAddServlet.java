package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Member member = new Member();

    member.setName(Prompt.getString(in, out, "이름? "));

    member.setEmail(Prompt.getString(in, out, "이메일? "));

    member.setPassword(Prompt.getString(in, out, "비밀번호? "));

    member.setPhoto(Prompt.getString(in, out, "사진? "));

    if (memberDao.insert(member) > 0) {
      out.println("등록했습니다.");

    } else {
      out.println("회원 등록에 실패했습니다.");
    }
  }
}
