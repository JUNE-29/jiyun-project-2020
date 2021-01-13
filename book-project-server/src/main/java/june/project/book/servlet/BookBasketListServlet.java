package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketListServlet implements Servlet {

  List<BookBasket> bookBasketList;

  public BookBasketListServlet(List<BookBasket> bookBasketList) {
    this.bookBasketList = bookBasketList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookBasketList);
  }
}
