package june.project.book.dao.proxy;

import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

// 프록시 객체는 항상 작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class BookmarkDaoProxy implements BookmarkDao {

  DaoProxyHelper daoProxyHelper;

  public BookmarkDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Bookmark bookmark) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/bookmark/add");
      out.writeObject(bookmark);
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
  public List<Bookmark> findAll() throws Exception {

    return (List<Bookmark>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/bookmark/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Bookmark>) in.readObject();
    });
  }

  @Override
  public Bookmark findByNo(int no) throws Exception {

    return (Bookmark) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/bookmark/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Bookmark) in.readObject();
    });
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/bookmark/update");
      out.writeObject(bookmark);

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

      out.writeUTF("/bookmark/delete");
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