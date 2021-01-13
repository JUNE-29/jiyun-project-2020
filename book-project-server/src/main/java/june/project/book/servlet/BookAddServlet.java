package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookAddServlet implements Servlet {
  List<BookBoard> bookBoard;

  public BookAddServlet(List<BookBoard> bookBoard) {
    this.bookBoard = bookBoard;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    BookBoard book = (BookBoard) in.readObject();

    int i = 0;
    for (; i < bookBoard.size(); i++) {
      if (bookBoard.get(i).getNo() == book.getNo()) {
        break;
      }
    }

    if (i == bookBoard.size()) {
      bookBoard.add(book);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
