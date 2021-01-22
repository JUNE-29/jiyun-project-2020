package june.project.book.dao.proxy;

import java.util.List;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;

// 프록시 객체는 항사 ㅇ작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class MemberDaoProxy implements MemberDao {

  DaoProxyHelper daoProxyHelper;

  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Member member) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {

    return (List<Member>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Member>) in.readObject();
    });
  }

  @Override
  public Member findByNo(int no) throws Exception {

    return (Member) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Member) in.readObject();
    });
  }

  @Override
  public int update(Member member) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/update");
      out.writeObject(member);

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @Override
  public int delete(int no) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }
}
