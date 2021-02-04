package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Member member = new Member();

    out.println("이름? ");
    out.println("!{}!");
    out.flush();
    member.setName(in.nextLine());

    out.println("이메일? ");
    out.println("!{}!");
    out.flush();
    member.setEmail(in.nextLine());

    out.println("비밀번호? ");
    out.println("!{}!");
    out.flush();
    member.setPassword(in.nextLine());

    out.println("사진? ");
    out.println("!{}!");
    out.flush();
    member.setPhoto(in.nextLine());

    if (memberDao.insert(member) > 0) {
      out.println("등록했습니다.");

    } else {
      out.println("회원 등록에 실패했습니다.");
    }
  }
}
