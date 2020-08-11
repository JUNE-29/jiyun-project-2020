package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardAddCommand implements Command {

  List<BookBoard> bookBoardList;

  Prompt prompt;

  public BookBoardAddCommand(Prompt prompt, List<BookBoard> list) {
    this.prompt = prompt;
    this.bookBoardList = list;
  }

  @Override
  public void execute() {
    BookBoard bookBoard = new BookBoard();

    bookBoard.setNo(prompt.inputInt("번호? "));

    bookBoard.setBookTitle(prompt.inputString("도서명? "));

    bookBoard.setAuthor(prompt.inputString("지은이? "));

    bookBoard.setPublisher(prompt.inputString("출판사? "));

    bookBoard.setCategories(prompt.inputString("카테고리? "));

    bookBoard.setPublishedDate(prompt.inputString("출판 연도? "));

    bookBoard.setContent(prompt.inputString("내용? "));

    bookBoard.setPhoto(prompt.inputString("이미지? "));

    bookBoard.setBookStatus(prompt.inputString("진행 상태? "));

    bookBoard.setScore(prompt.inputFloat("책에 대한 점수(5.0점만점)? "));

    bookBoard.setDate(new Date(System.currentTimeMillis()));

    bookBoard.setViewCount(0);

    bookBoardList.add(bookBoard);

    System.out.println("저장하였습니다.");
  }
}
