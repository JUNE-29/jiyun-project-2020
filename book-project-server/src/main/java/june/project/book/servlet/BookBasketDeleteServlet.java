package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.dao.BookBasketObjectFileDao;

public class BookBasketDeleteServlet implements Servlet {

  BookBasketObjectFileDao bookBasketDao;

  public BookBasketDeleteServlet(BookBasketObjectFileDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    if (bookBasketDao.delete(no) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}