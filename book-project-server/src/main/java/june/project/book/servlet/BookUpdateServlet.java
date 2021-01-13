package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookUpdateServlet implements Servlet {

  List<BookBoard> bookBoard;

  public BookUpdateServlet(List<BookBoard> bookBoard) {
    this.bookBoard = bookBoard;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    BookBoard book = (BookBoard) in.readObject();

    int index = -1;
    for (int i = 0; i < bookBoard.size(); i++) {
      if (bookBoard.get(i).getNo() == book.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      bookBoard.set(index, book);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
