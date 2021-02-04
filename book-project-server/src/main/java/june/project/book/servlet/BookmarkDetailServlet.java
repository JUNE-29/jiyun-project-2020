package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkDetailServlet implements Servlet {

  BookmarkDao bookmarkDao;

  public BookmarkDetailServlet(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    Bookmark bookmark = bookmarkDao.findByNo(no);

    if (bookmark != null) {
      out.printf("번호: %d\n", bookmark.getNo());
      out.printf("제목: %s\n", bookmark.getTitle());
      out.printf("도서명: %s\n", bookmark.getBookTitle());
      out.printf("지은이: %s\n", bookmark.getAuthor());
      out.printf("출판사: %s\n", bookmark.getPublisher());
      out.printf("필사 내용: %s\n", bookmark.getContent());
      out.printf("사진:%s\n", bookmark.getPhoto());
      out.printf("등록일:%s\n", bookmark.getDate());

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
