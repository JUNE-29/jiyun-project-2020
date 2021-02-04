package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.MemberDao;

public class MemberDeleteServlet implements Servlet {

  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (memberDao.delete(no) > 0) {
      out.println("삭제했습니다.");

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
