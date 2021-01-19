package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

public class BookBasketUpdateServlet implements Servlet {

  BookBasketDao bookBasketDao;

  public BookBasketUpdateServlet(BookBasketDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBasket bookBasket = (BookBasket) in.readObject();

    if (bookBasketDao.update(bookBasket) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
