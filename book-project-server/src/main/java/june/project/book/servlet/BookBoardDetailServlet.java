package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.json.BookBoardJsonFileDao;
import june.project.book.domain.BookBoard;

public class BookBoardDetailServlet implements Servlet {

  BookBoardJsonFileDao bookBoardDao;

  public BookBoardDetailServlet(BookBoardJsonFileDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    BookBoard bookBoard = bookBoardDao.findByNo(no);

    if (bookBoard != null) {
      out.writeUTF("OK");
      out.writeObject(bookBoard);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
