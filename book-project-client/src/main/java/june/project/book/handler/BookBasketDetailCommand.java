package june.project.book.handler;

import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketDetailCommand implements Command {

  BookBasketDao bookBasketDao;
  Prompt prompt;

  public BookBasketDetailCommand(BookBasketDao bookBasketDao, Prompt prompt) {
    this.bookBasketDao = bookBasketDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      BookBasket bookBasket = bookBasketDao.findByNo(no);

      System.out.printf("번호: %d\n", bookBasket.getNo());
      System.out.printf("도서명: %s\n", bookBasket.getBookTitle());
      System.out.printf("지은이: %s\n", bookBasket.getAuthor());
      System.out.printf("출판사: %s\n", bookBasket.getPublisher());
      System.out.printf("출판 연도: %s\n", bookBasket.getPublishedDate());
      System.out.printf("카테고리: %s\n", bookBasket.getCategories());
      System.out.printf("즐겨찾기 한 이유: %s\n", bookBasket.getMemo());

    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
