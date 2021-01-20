package june.project.book.handler;

import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketAddCommand implements Command {

  BookBasketDao bookBasketDao;
  Prompt prompt;

  public BookBasketAddCommand(BookBasketDao bookBasketDao, Prompt prompt) {
    this.bookBasketDao = bookBasketDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    BookBasket bookBasket = new BookBasket();

    bookBasket.setNo(prompt.inputInt("번호? "));

    bookBasket.setBookTitle(prompt.inputString("도서명? "));

    bookBasket.setAuthor(prompt.inputString("지은이? "));

    bookBasket.setPublisher(prompt.inputString("출판사? "));

    bookBasket.setCategories(prompt.inputString("카테고리? "));

    bookBasket.setPublishedDate(prompt.inputString("출판 연도? "));

    bookBasket.setMemo(prompt.inputString("즐겨찾기 한 이유? "));

    try {
      bookBasketDao.insert(bookBasket);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("저장 실패!");
    }

  }
}
