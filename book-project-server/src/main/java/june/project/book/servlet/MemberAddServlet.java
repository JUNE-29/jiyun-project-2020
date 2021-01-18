package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.MemberJsonFileDao;
import june.project.book.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberAddServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Member member = (Member) in.readObject();

    if (memberDao.insert(member) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
