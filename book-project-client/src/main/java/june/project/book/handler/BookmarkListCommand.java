package june.project.book.handler;

import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkListCommand implements Command {

  BookmarkDao bookmarkDao;

  public BookmarkListCommand(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void execute() {

    try {
      List<Bookmark> bookmark = bookmarkDao.findAll();
      for (Bookmark bm : bookmark) {
        System.out.printf("%d, %s, %s, %s, %s\n", bm.getNo(), bm.getTitle(), bm.getBookTitle(),
            bm.getAuthor(), bm.getDate());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
