package june.project.book.dao.proxy;

import java.util.List;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

// 프록시 객체는 항상 작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class BookBoardDaoProxy implements BookBoardDao {

  DaoProxyHelper daoProxyHelper;

  public BookBoardDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(BookBoard BookBoard) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/book/add");
      out.writeObject(BookBoard);
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
  public List<BookBoard> findAll() throws Exception {

    return (List<BookBoard>) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/book/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<BookBoard>) in.readObject();
    });
  }

  @Override
  public BookBoard findByNo(int no) throws Exception {

    return (BookBoard) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/book/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (BookBoard) in.readObject();
    });
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/book/update");
      out.writeObject(bookBoard);

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

      out.writeUTF("/book/delete");
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
