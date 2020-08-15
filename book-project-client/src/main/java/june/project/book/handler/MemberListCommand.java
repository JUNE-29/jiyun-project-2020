package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Member;

public class MemberListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;


  public MemberListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {

      out.writeUTF("/member/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Member> member = (List<Member>) in.readObject();

      for (Member m : member) {
        System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
            m.getRegisteredDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
