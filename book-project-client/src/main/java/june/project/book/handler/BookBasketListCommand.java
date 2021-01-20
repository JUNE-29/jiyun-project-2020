package june.project.book.handler;

import java.util.List;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

public class BookBasketListCommand implements Command {

  BookBasketDao bookBasketDao;

  public BookBasketListCommand(BookBasketDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public void execute() {

    try {
      List<BookBasket> bookBasket = bookBasketDao.findAll();
      for (BookBasket basket : bookBasket) {
        System.out.printf("%d, %s, %s\n", //
            basket.getNo(), basket.getBookTitle(), basket.getCategories());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
