package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberAddCommand implements Command {

  List<Member> memberList;

  public Prompt prompt;

  public MemberAddCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() {
    Member member = new Member();

    member.setNo(prompt.inputInt("번호? "));

    member.setName(prompt.inputString("이름? "));

    member.setEmail(prompt.inputString("이메일? "));

    member.setPassword(prompt.inputString("비밀번호? "));

    member.setPhoto(prompt.inputString("사진? "));

    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.memberList.add(member);

    System.out.println("저장하였습니다.");

  }
}
