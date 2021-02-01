package june.project.book.handler;

import java.util.List;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardListCommand implements Command {

  BookBoardDao bookBoardDao;

  public BookBoardListCommand(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void execute() {

    try {

      List<BookBoard> bookBoard = bookBoardDao.findAll();
      for (BookBoard book : bookBoard) {
        System.out.printf("%d, %s, %d점, %s, %d\n", //
            book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(),
            book.getBookStatus());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
