package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Member;

public class MemberUpdateServlet implements Servlet {

  List<Member> memberList;

  public MemberUpdateServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Member member = (Member) in.readObject();

    int index = -1;
    for (int i = 0; i < memberList.size(); i++) {
      if (memberList.get(i).getNo() == member.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      memberList.set(index, member);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
