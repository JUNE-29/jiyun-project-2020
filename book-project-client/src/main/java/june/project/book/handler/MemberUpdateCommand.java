package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberUpdateCommand implements Command {
  List<Member> memberList;

  public Prompt prompt;

  public MemberUpdateCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() {
    int index = indexOfMember(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();

    newMember.setNo(oldMember.getNo());

    newMember.setName(
        prompt.inputString(String.format("이름(%s)? ", oldMember.getName()), oldMember.getName()));

    newMember.setEmail(
        prompt.inputString(String.format("이메일(%s)? ", oldMember.getEmail()), oldMember.getEmail()));

    newMember.setPassword(prompt.inputString(String.format("비밀번호(%s)? ", oldMember.getPassword()),
        oldMember.getPassword()));

    newMember.setPhoto(
        prompt.inputString(String.format("사진(%s)? ", oldMember.getPhoto()), oldMember.getPhoto()));

    newMember.setRegisteredDate(new Date(System.currentTimeMillis()));

    if (oldMember.equals(newMember)) {
      System.out.println("회원을 변경을 취소했습니다.");
      return;
    }

    this.memberList.set(index, newMember);
    System.out.println("회원을 변경했습니다.");
  }

  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.size(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
