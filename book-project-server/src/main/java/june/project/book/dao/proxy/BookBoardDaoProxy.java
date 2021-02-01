package june.project.book.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

// 프록시 객체는 항상 작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class BookBoardDaoProxy implements BookBoardDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public BookBoardDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(BookBoard BookBoard) throws Exception {

    out.writeUTF("/book/add");
    out.writeObject(BookBoard);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<BookBoard> findAll() throws Exception {

    out.writeUTF("/book/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<BookBoard>) in.readObject();
  }

  @Override
  public BookBoard findByNo(int no) throws Exception {

    out.writeUTF("/book/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (BookBoard) in.readObject();
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {

    out.writeUTF("/book/update");
    out.writeObject(bookBoard);

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {

    out.writeUTF("/book/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }
}