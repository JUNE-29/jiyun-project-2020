package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketObjectFileDao;
import june.project.book.domain.BookBasket;

public class BookBasketAddServlet implements Servlet {

  BookBasketObjectFileDao bookBasketDao;

  public BookBasketAddServlet(BookBasketObjectFileDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBasket bookBasket = (BookBasket) in.readObject();

    if (bookBasketDao.insert(bookBasket) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
