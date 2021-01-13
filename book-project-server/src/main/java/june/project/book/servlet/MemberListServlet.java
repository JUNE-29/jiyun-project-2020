package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Member;

public class MemberListServlet implements Servlet {

  List<Member> memberList;

  public MemberListServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberList);
  }
}
