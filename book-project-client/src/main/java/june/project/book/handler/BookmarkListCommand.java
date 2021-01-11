package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public BookmarkListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {
      out.writeUTF("/bookmark/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Bookmark> bookmark = (List<Bookmark>) in.readObject();
      for (Bookmark bm : bookmark) {
        System.out.printf("%d, %s, %s, %s, %s\n", bm.getNo(), bm.getTitle(), bm.getBookTitle(),
            bm.getAuthor(), bm.getDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
