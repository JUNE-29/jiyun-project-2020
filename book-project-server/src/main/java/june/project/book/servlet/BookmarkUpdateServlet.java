package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkUpdateServlet implements Servlet {

  List<Bookmark> bookmarks;

  public BookmarkUpdateServlet(List<Bookmark> bookmarks) {
    this.bookmarks = bookmarks;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Bookmark bookmark = (Bookmark) in.readObject();

    int index = -1;
    for (int i = 0; i < bookmarks.size(); i++) {
      if (bookmarks.get(i).getNo() == bookmark.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      bookmarks.set(index, bookmark);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
