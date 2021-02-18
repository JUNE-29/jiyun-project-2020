package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

public class BookmarkListServlet implements Servlet {

  BookmarkService bookmarkService;

  public BookmarkListServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Bookmark> bookmark = bookmarkService.list();
    for (Bookmark bm : bookmark) {
      out.printf("%d, %s, %s, %s, %s\n", bm.getNo(), bm.getTitle(), bm.getBookTitle(),
          bm.getAuthor(), bm.getDate());
    }
  }
}
