package june.project.book.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

// 프록시 객체는 항상 작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class BookBasketDaoProxy implements BookBasketDao {

  DaoProxyHelper daoProxyHelper;

  public BookBasketDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<BookBasket> findAll() throws Exception {
    Worker worker = new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/basket/list");
        out.flush();
        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    };
    Object result = daoProxyHelper.request(worker);
    return (List<BookBasket>) result;
  }

  @Override
  public int insert(BookBasket bookBasket) throws Exception {

    class InsertWorker implements Worker {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/basket/add");
        out.writeObject(bookBasket);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    }

    InsertWorker worker = new InsertWorker();
    int resultState = (int) daoProxyHelper.request(worker);

    return resultState;
  }


  @Override
  public BookBasket findByNo(int no) throws Exception {

    Object result = daoProxyHelper.request(new Worker() {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {

        out.writeUTF("/basket/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    });
    return (BookBasket) result;
  }

  @Override
  public int update(BookBasket bookBasket) throws Exception {

    return (int) daoProxyHelper.request(new Worker() {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/basket/update");
        out.writeObject(bookBasket);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    });
  }

  @Override
  public int delete(int no) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/basket/delete");
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
