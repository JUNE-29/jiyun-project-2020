package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketDao;

public class BookBasketListServlet implements Servlet {

  BookBasketDao bookBasketDao;

  public BookBasketListServlet(BookBasketDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookBasketDao.findAll());
  }
}
