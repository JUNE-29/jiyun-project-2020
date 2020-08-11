package june.project.book.handler;

import java.util.Iterator;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookBoardListCommand implements Command {

  List<BookBoard> bookBoardList;

  public BookBoardListCommand(List<BookBoard> list) {
    this.bookBoardList = list;
  }

  @Override
  public void execute() {
    Iterator<BookBoard> iterator = bookBoardList.iterator();
    while (iterator.hasNext()) {
      BookBoard book = iterator.next();
      System.out.printf("%d, %s, %1.1f점, %s, %d, %s\n", //
          book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(), book.getViewCount(),
          book.getBookStatus());
    }
  }
}
