package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketAddServlet implements Servlet {

  List<BookBasket> bookBasketList;

  public BookBasketAddServlet(List<BookBasket> bookBasketList) {
    this.bookBasketList = bookBasketList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    BookBasket bookBasket = (BookBasket) in.readObject();

    int i = 0;
    for (; i < bookBasketList.size(); i++) {
      if (bookBasketList.get(i).getNo() == bookBasket.getNo()) {
        break;
      }
    }

    if (i == bookBasketList.size()) {
      bookBasketList.add(bookBasket);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
