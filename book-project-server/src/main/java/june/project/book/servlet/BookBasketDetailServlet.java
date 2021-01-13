package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketDetailServlet implements Servlet {

  List<BookBasket> bookBasketList;

  public BookBasketDetailServlet(List<BookBasket> bookBasketList) {
    this.bookBasketList = bookBasketList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    BookBasket bookBasket = null;
    for (BookBasket bb : bookBasketList) {
      if (bb.getNo() == no) {
        bookBasket = bb;
      }
    }

    if (bookBasket != null) {
      out.writeUTF("OK");
      out.writeObject(bookBasket);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
