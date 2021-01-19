package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

public class BookBasketDetailServlet implements Servlet {

  BookBasketDao bookBasketDao;

  public BookBasketDetailServlet(BookBasketDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    BookBasket bookBasket = bookBasketDao.findByNo(no);

    if (bookBasket != null) {
      out.writeUTF("OK");
      out.writeObject(bookBasket);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
