package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkAddServlet implements Servlet {

  List<Bookmark> bookmarks;

  public BookmarkAddServlet(List<Bookmark> bookmarks) {
    this.bookmarks = bookmarks;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Bookmark bookmark = (Bookmark) in.readObject();

    int i = 0;
    for (; i < bookmarks.size(); i++) {
      if (bookmarks.get(i).getNo() == bookmark.getNo()) {
        break;
      }
    }

    if (i == bookmarks.size()) {
      bookmarks.add(bookmark);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}
