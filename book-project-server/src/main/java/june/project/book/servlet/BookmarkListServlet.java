package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkListServlet implements Servlet {

  BookmarkDao bookmarkDao;

  public BookmarkListServlet(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Bookmark> bookmark = bookmarkDao.findAll();
    for (Bookmark bm : bookmark) {
      out.printf("%d, %s, %s, %s, %s\n", bm.getNo(), bm.getTitle(), bm.getBookTitle(),
          bm.getAuthor(), bm.getDate());
    }
  }
}
