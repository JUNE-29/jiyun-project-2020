package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.BookmarkJsonFileDao;
import june.project.book.domain.Bookmark;

public class BookmarkUpdateServlet implements Servlet {

  BookmarkJsonFileDao bookmarkDao;

  public BookmarkUpdateServlet(BookmarkJsonFileDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Bookmark bookmark = (Bookmark) in.readObject();

    if (bookmarkDao.update(bookmark) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
