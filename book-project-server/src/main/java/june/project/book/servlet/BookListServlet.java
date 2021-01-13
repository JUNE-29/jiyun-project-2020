package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookListServlet implements Servlet {

  List<BookBoard> bookBoard;

  public BookListServlet(List<BookBoard> bookBoard) {
    this.bookBoard = bookBoard;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookBoard);
  }
}
