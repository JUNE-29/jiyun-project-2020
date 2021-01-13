package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Member;

public class MemberAddServlet implements Servlet {

  List<Member> memberList;

  public MemberAddServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    int i = 0;
    for (; i < memberList.size(); i++) {
      if (memberList.get(i).getNo() == member.getNo()) {
        break;
      }
    }
    if (i == memberList.size()) {
      memberList.add(member);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
