package june.project.book.handler;

import june.project.book.dao.BookBasketDao;
import june.project.util.Prompt;

public class BookBasketDeleteCommand implements Command {

  BookBasketDao bookBasketDao;
  Prompt prompt;

  public BookBasketDeleteCommand(BookBasketDao bookBasketDao, Prompt prompt) {
    this.bookBasketDao = bookBasketDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      bookBasketDao.delete(no);
      System.out.println("즐겨찾는 도서를 삭제했습니다.");

    } catch (Exception e) {

      System.out.println("삭제 실패!");
    }
  }
}
