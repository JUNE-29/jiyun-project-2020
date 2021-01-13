package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketDeleteServlet implements Servlet {

  List<BookBasket> bookBasketList;

  public BookBasketDeleteServlet(List<BookBasket> bookBasketList) {
    this.bookBasketList = bookBasketList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    int index = -1;
    for (int i = 0; i < bookBasketList.size(); i++) {
      if (bookBasketList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      bookBasketList.remove(index);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
