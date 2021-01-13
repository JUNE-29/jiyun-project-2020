package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketUpdateServlet implements Servlet {

  List<BookBasket> bookBasketList;

  public BookBasketUpdateServlet(List<BookBasket> bookBasketList) {
    this.bookBasketList = bookBasketList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBasket bookBasket = (BookBasket) in.readObject();

    int index = -1;
    for (int i = 0; i < bookBasketList.size(); i++) {
      if (bookBasketList.get(i).getNo() == bookBasket.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      bookBasketList.set(index, bookBasket);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
