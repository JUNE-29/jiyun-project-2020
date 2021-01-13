package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Member;

public class MemberDetailServlet implements Servlet {

  List<Member> memberList;

  public MemberDetailServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Member member = null;
    for (Member m : memberList) {
      if (m.getNo() == no) {
        member = m;
        break;
      }
    }

    if (member != null) {
      out.writeUTF("OK");
      out.writeObject(member);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
