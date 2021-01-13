package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkListServlet implements Servlet {

  List<Bookmark> bookmarks;

  public BookmarkListServlet(List<Bookmark> bookmarks) {
    this.bookmarks = bookmarks;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(bookmarks);
  }

}
