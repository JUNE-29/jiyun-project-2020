package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

public class BookBasketAddServlet implements Servlet {

  // DAO 클래스를 구체적으로 지정하기 보다는 인터페이스를 지정함으로써
  // 향후 다른 구현체로 교체하기 쉽도록 한다.

  BookBasketDao bookBasketDao;

  public BookBasketAddServlet(BookBasketDao bookBasketDao) {
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
