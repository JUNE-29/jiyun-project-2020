package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookmarkObjectFileDao;

public class BookmarkListServlet implements Servlet {

  BookmarkObjectFileDao bookmarkDao;

  public BookmarkListServlet(BookmarkObjectFileDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookmarkDao.findAll());
  }

}
