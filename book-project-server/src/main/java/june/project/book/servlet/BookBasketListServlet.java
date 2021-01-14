package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketObjectFileDao;

public class BookBasketListServlet implements Servlet {

  BookBasketObjectFileDao bookBasketDao;

  public BookBasketListServlet(BookBasketObjectFileDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookBasketDao.findAll());
  }
}
