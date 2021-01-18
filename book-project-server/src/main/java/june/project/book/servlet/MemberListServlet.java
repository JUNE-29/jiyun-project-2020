package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.MemberJsonFileDao;

public class MemberListServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberListServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberDao.findAll());
  }
}
