package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.BookBoardJsonFileDao;
import june.project.book.domain.BookBoard;

public class BookBoardUpdateServlet implements Servlet {

  BookBoardJsonFileDao bookBoardDao;

  public BookBoardUpdateServlet(BookBoardJsonFileDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBoard bookBoard = (BookBoard) in.readObject();

    if (bookBoardDao.update(bookBoard) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }

  }
}
