package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkUpdateServlet implements Servlet {

  BookmarkDao bookmarkDao;

  public BookmarkUpdateServlet(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    Bookmark old = bookmarkDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Bookmark bookmark = new Bookmark();

    bookmark.setNo(no);

    out.printf("제목(%s)? \n", old.getTitle());
    out.println("!{}!");
    out.flush();
    bookmark.setTitle(in.nextLine());

    out.printf("도서명(%s)? \n", old.getBookTitle());
    out.println("!{}!");
    out.flush();
    bookmark.setBookTitle(in.nextLine());

    out.printf("지은이(%s)? \n", old.getAuthor());
    out.println("!{}!");
    out.flush();
    bookmark.setAuthor(in.nextLine());

    out.printf("출판사(%s)? \n", old.getPublisher());
    out.println("!{}!");
    out.flush();
    bookmark.setPublisher(in.nextLine());

    out.printf("내용(%s)? \n", old.getContent());
    out.println("!{}!");
    out.flush();
    bookmark.setContent(in.nextLine());

    out.printf("이미지(%s)? \n", old.getPhoto());
    out.println("!{}!");
    out.flush();
    bookmark.setPhoto(in.nextLine());

    if (bookmarkDao.update(bookmark) > 0) {
      out.println("변경했습니다.");

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
