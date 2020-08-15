package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public Prompt prompt;

  public MemberAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
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

    try {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
