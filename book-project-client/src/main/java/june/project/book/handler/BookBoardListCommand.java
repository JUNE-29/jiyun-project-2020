package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBoard;

public class BookBoardListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public BookBoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/book/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF()); // 서버의 메시지(왜 실패했는지)를 읽는다.
        return;
      }

      List<BookBoard> bookBoard = (List<BookBoard>) in.readObject();
      for (BookBoard book : bookBoard) {
        System.out.printf("%d, %s, %1.1f점, %s, %d, %s\n", //
            book.getNo(), book.getBookTitle(), book.getScore(), book.getDate(), book.getViewCount(),
            book.getBookStatus());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
