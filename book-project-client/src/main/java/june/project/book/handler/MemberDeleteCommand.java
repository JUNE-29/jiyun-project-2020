package june.project.book.handler;

import june.project.book.dao.MemberDao;
import june.project.util.Prompt;

public class MemberDeleteCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      memberDao.delete(no);
      System.out.println("회원을 삭제하였습니다.");

    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
