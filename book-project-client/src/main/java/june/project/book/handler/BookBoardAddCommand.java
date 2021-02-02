package june.project.book.handler;

import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardAddCommand implements Command {

  Prompt prompt;
  BookBoardDao bookBoardDao;

  public BookBoardAddCommand(BookBoardDao bookBoardDao, Prompt prompt) {
    this.bookBoardDao = bookBoardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    BookBoard bookBoard = new BookBoard();
    bookBoard.setBookTitle(prompt.inputString("도서명? "));
    bookBoard.setAuthor(prompt.inputString("지은이? "));
    bookBoard.setPublisher(prompt.inputString("출판사? "));
    bookBoard.setCategories(prompt.inputString("카테고리? "));
    bookBoard.setPublishedDate(prompt.inputString("출판 연도? "));
    bookBoard.setContent(prompt.inputString("내용(메모)? "));
    bookBoard.setPhoto(prompt.inputString("이미지? "));
    bookBoard.setBookStatus(prompt.inputInt("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정)? "));
    bookBoard.setScore(prompt.inputInt("책에 대한 점수(5점만점)? "));

    try {
      bookBoardDao.insert(bookBoard);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("저장 실패!");
      e.printStackTrace();
    }
  }
}
