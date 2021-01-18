package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.BookBoardJsonFileDao;

public class BookBoardListServlet implements Servlet {

  BookBoardJsonFileDao bookBoardDao;

  public BookBoardListServlet(BookBoardJsonFileDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookBoardDao.findAll());
  }
}
