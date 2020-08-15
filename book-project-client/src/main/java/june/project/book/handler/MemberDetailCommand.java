package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.domain.Member;
import june.project.util.Prompt;

public class MemberDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public Prompt prompt;

  public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Member member = (Member) in.readObject();
      System.out.printf("번호: %d \n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("암호: %s\n", member.getPassword());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("가입일: %s \n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
