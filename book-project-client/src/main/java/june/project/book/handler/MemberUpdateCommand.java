package june.project.book.handler;

import java.sql.Date;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberUpdateCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;

  public MemberUpdateCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      Member oldMember = null;

      try {

        oldMember = memberDao.findByNo(no);

      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());

      newMember.setName(
          prompt.inputString(String.format("이름(%s)? ", oldMember.getName()), oldMember.getName()));

      newMember.setEmail(prompt.inputString(String.format("이메일(%s)? ", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setPassword(prompt.inputString(String.format("비밀번호(%s)? ", oldMember.getPassword()),
          oldMember.getPassword()));

      newMember.setPhoto(prompt.inputString(String.format("사진(%s)? ", oldMember.getPhoto()),
          oldMember.getPhoto()));

      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));

      if (oldMember.equals(newMember)) {
        System.out.println("회원을 변경을 취소했습니다.");
        return;
      }

      memberDao.update(newMember);
      System.out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }
}
