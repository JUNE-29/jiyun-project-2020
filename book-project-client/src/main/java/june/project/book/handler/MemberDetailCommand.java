package june.project.book.handler;

import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberDetailCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;

  public MemberDetailCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      Member member = memberDao.findByNo(no);

      System.out.printf("번호: %d \n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("암호: %s\n", member.getPassword());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("가입일: %s \n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("조회 실패!");
      e.printStackTrace();
    }
  }
}
