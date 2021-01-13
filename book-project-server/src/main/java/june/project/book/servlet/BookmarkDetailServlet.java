package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkDetailServlet implements Servlet {

  List<Bookmark> bookmarks;

  public BookmarkDetailServlet(List<Bookmark> bookmarks) {
    this.bookmarks = bookmarks;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    Bookmark bookmark = null;
    for (Bookmark bm : bookmarks) {
      if (bm.getNo() == no) {
        bookmark = bm;
        break;
      }
    }

    if (bookmark != null) {
      out.writeUTF("OK");
      out.writeObject(bookmark);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
