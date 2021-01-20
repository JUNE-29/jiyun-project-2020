package june.project.book.handler;

import june.project.book.dao.BookBoardDao;
import june.project.util.Prompt;

public class BookBoardDeleteCommand implements Command {

  Prompt prompt;
  BookBoardDao bookBoardDao;

  public BookBoardDeleteCommand(BookBoardDao bookBoardDao, Prompt prompt) {
    this.bookBoardDao = bookBoardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      bookBoardDao.delete(no);
      System.out.println("도서 게시판의 정보를 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
