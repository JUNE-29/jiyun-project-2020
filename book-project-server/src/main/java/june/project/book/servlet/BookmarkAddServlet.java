package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookmarkObjectFileDao;
import june.project.book.domain.Bookmark;

public class BookmarkAddServlet implements Servlet {

  BookmarkObjectFileDao bookmarkDao;

  public BookmarkAddServlet(BookmarkObjectFileDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Bookmark bookmark = (Bookmark) in.readObject();

    if (bookmarkDao.insert(bookmark) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
