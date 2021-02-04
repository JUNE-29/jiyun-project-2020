package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkAddServlet implements Servlet {

  BookmarkDao bookmarkDao;

  public BookmarkAddServlet(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Bookmark bookmark = new Bookmark();

    out.println("게시글 제목? ");
    out.println("!{}!");
    out.flush();
    bookmark.setTitle(in.nextLine());

    out.println("도서명? ");
    out.println("!{}!");
    out.flush();
    bookmark.setBookTitle(in.nextLine());

    out.println("지은이? ");
    out.println("!{}!");
    out.flush();
    bookmark.setAuthor(in.nextLine());

    out.println("출판사? ");
    out.println("!{}!");
    out.flush();
    bookmark.setPublisher(in.nextLine());

    out.println("내용? ");
    out.println("!{}!");
    out.flush();
    bookmark.setContent(in.nextLine());

    out.println("이미지? ");
    out.println("!{}!");
    out.flush();
    bookmark.setPhoto(in.nextLine());

    if (bookmarkDao.insert(bookmark) > 0) {
      out.println("저장하였습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
