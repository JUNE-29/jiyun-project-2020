package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.BookmarkJsonFileDao;
import june.project.book.domain.Bookmark;

public class BookmarkDetailServlet implements Servlet {

  BookmarkJsonFileDao bookmarkDao;

  public BookmarkDetailServlet(BookmarkJsonFileDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();
    Bookmark bookmark = bookmarkDao.findByNo(no);

    if (bookmark != null) {
      out.writeUTF("OK");
      out.writeObject(bookmark);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
