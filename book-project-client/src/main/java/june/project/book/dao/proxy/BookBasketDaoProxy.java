package june.project.book.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

// 프록시 객체는 항상 작업 객체와 동일한 인터페이스를 구현해야 한다.
// => 마치 자신이 작업 객체인양 보이기 위함이다.

public class BookBasketDaoProxy implements BookBasketDao {

  String host;
  int port;

  public BookBasketDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<BookBasket> findAll() throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/basket/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<BookBasket>) in.readObject();
    }
  }

  @Override
  public int insert(BookBasket bookBasket) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

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

  @Override
  public BookBasket findByNo(int no) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/basket/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (BookBasket) in.readObject();
    }
  }

  @Override
  public int update(BookBasket bookBasket) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/basket/update");
      out.writeObject(bookBasket);

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/basket/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    }
  }
}
