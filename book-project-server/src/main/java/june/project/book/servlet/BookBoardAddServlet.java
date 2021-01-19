package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardAddServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardAddServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBoard bookBoard = (BookBoard) in.readObject();

    if (bookBoardDao.insert(bookBoard) > 0) { // 등록(저장)했다면
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
