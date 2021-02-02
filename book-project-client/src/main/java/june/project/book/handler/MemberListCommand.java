package june.project.book.handler;

import java.util.List;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;

public class MemberListCommand implements Command {

  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {

    try {

      List<Member> member = memberDao.findAll();
      for (Member m : member) {
        System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
            m.getRegisteredDate());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
      e.printStackTrace();
    }
  }
}
