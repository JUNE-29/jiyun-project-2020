package june.project.book.handler;

import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketUpdateCommand implements Command {

  BookBasketDao bookBasketDao;
  Prompt prompt;

  public BookBasketUpdateCommand(BookBasketDao bookBasketDao, Prompt prompt) {
    this.bookBasketDao = bookBasketDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      BookBasket oldBasket = null;
      try {
        oldBasket = bookBasketDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      BookBasket newBasket = new BookBasket();

      newBasket.setNo(oldBasket.getNo());

      newBasket.setBookTitle(prompt.inputString( //
          String.format("도서명(%s)?", oldBasket.getBookTitle()), oldBasket.getBookTitle()));

      newBasket.setAuthor(prompt.inputString( //
          String.format("지은이(%s)? ", oldBasket.getAuthor()), oldBasket.getAuthor()));

      newBasket.setPublisher(prompt.inputString( //
          String.format("출판사(%s)? ", oldBasket.getPublisher()), oldBasket.getPublisher()));

      newBasket.setPublishedDate( //
          prompt.inputString(String.format("출판 연도(%s)? ", oldBasket.getPublishedDate()),
              oldBasket.getPublishedDate()));

      newBasket.setCategories( //
          prompt.inputString(String.format("카테고리(%s)? ", oldBasket.getCategories()),
              oldBasket.getCategories()));

      newBasket.setMemo( //
          prompt.inputString(String.format("즐겨 찾기 한 이유(%s)? ", oldBasket.getMemo()),
              oldBasket.getMemo()));

      if (oldBasket.equals(newBasket)) {
        System.out.println("즐겨찾는 도서의 변경을 취소했습니다.");
        return;
      }

      bookBasketDao.update(newBasket);
      System.out.println("즐겨찾는 도서를 변경했습니다.");

    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }
}
