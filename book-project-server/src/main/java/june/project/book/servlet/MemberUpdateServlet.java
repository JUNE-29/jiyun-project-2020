package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    Member old = memberDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = new Member();

    member.setNo(no);

    out.printf("이름(%s)? \n", old.getName());
    out.println("!{}!");
    out.flush();
    member.setName(in.nextLine());

    out.printf("이메일(%s)? \n", old.getEmail());
    out.println("!{}!");
    out.flush();
    member.setEmail(in.nextLine());

    out.printf("비밀번호(%s)? \n", old.getPassword());
    out.println("!{}!");
    out.flush();
    member.setPassword(in.nextLine());

    out.printf("사진(%s)? \n", old.getPhoto());
    out.println("!{}!");
    out.flush();
    member.setPhoto(in.nextLine());

    if (memberDao.update(member) > 0) {
      out.println("변경했습니다.");

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
