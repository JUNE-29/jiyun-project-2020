package june.project.book.handler;

import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardUpdateCommand implements Command {

  Prompt prompt;
  BookBoardDao bookBoardDao;

  public BookBoardUpdateCommand(BookBoardDao bookBoardDao, Prompt prompt) {
    this.bookBoardDao = bookBoardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      BookBoard oldBook = null;

      try {
        oldBook = bookBoardDao.findByNo(no);

      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      BookBoard newBook = new BookBoard();

      newBook.setNo(oldBook.getNo());

      newBook.setBookTitle(//
          prompt.inputString(String.format("도서명(%s)? ", oldBook.getBookTitle()),
              oldBook.getBookTitle()));

      newBook.setAuthor(//
          prompt.inputString(String.format("지은이(%s)? ", oldBook.getAuthor()), oldBook.getAuthor()));

      newBook.setPublisher(//
          prompt.inputString(String.format("출판사(%s)? ", oldBook.getPublisher()),
              oldBook.getPublisher()));

      newBook.setCategories(//
          prompt.inputString(String.format("카테고리(%s)? ", oldBook.getCategories()),
              oldBook.getCategories()));

      newBook.setPublishedDate(//
          prompt.inputString(String.format("출판 연도(%s)? ", oldBook.getPublishedDate()),
              oldBook.getPublishedDate()));

      newBook.setContent(//
          prompt.inputString(String.format("내용(%s)? ", oldBook.getContent()),
              oldBook.getContent()));

      newBook.setPhoto(//
          prompt.inputString(String.format("이미지(%s)? ", oldBook.getPhoto()), oldBook.getPhoto()));

      newBook.setScore(//
          prompt.inputInt(String.format("평가(%d점)? ", oldBook.getScore()), oldBook.getScore()));

      newBook.setBookStatus(//
          prompt.inputInt(
              String.format("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정) (%d) ? ", oldBook.getBookStatus()),
              oldBook.getBookStatus()));

      bookBoardDao.update(newBook);
      System.out.println("읽은 도서 정보를 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
