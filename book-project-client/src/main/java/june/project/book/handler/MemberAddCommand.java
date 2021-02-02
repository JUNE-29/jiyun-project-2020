package june.project.book.handler;

import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberAddCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    Member member = new Member();

    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("비밀번호? "));
    member.setPhoto(prompt.inputString("사진? "));

    try {

      memberDao.insert(member);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("저장 실패!");
      e.printStackTrace();
    }
  }
}
