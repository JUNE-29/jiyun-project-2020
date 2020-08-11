package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardUpdateCommand implements Command {

  List<BookBoard> bookBoardList;

  Prompt prompt;

  public BookBoardUpdateCommand(Prompt prompt, List<BookBoard> list) {
    this.prompt = prompt;
    this.bookBoardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecommendation(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("도서 게시판의 번호가 유효하지 않습니다.");
      return;
    }

    BookBoard oldBook = this.bookBoardList.get(index);
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
        prompt.inputString(String.format("내용(%s)? ", oldBook.getContent()), oldBook.getContent()));

    newBook.setPhoto(//
        prompt.inputString(String.format("이미지(%s)? ", oldBook.getPhoto()), oldBook.getPhoto()));

    newBook.setScore(//
        prompt.inputFloat(String.format("평가(%1.1f점)? ", oldBook.getScore()), oldBook.getScore()));

    newBook.setBookStatus(//
        prompt.inputString(String.format("진행 상태(%s)? ", oldBook.getBookStatus()),
            oldBook.getBookStatus()));

    newBook.setDate(new Date(System.currentTimeMillis()));
    newBook.setViewCount(oldBook.getViewCount());

    if (oldBook.equals(newBook)) {
      System.out.println("읽은 도서 정보의 변경을 취소했습니다.");
      return;
    }

    this.bookBoardList.set(index, newBook);
    System.out.println("읽은 도서 정보를 변경했습니다.");
  }

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.bookBoardList.size(); i++) {
      if (this.bookBoardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
