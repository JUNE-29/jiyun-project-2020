package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookDetailServlet implements Servlet {

  List<BookBoard> bookBoard;

  public BookDetailServlet(List<BookBoard> bookBoard) {
    this.bookBoard = bookBoard;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    BookBoard book = null;
    for (BookBoard b : bookBoard) {
      if (b.getNo() == no) {
        book = b;
        break;
      }
    }

    if (book != null) {
      out.writeUTF("OK");
      out.writeObject(book);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
