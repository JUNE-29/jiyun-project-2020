package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  BookmarkDao bookmarkDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, BookmarkDao bookmarkDao) {
    this.photoBoardDao = photoBoardDao;
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("북마크 번호? ");
    out.println("!{}!");
    out.flush();

    int bookmarkNo = Integer.parseInt(in.nextLine());

    Bookmark bookmark = bookmarkDao.findByNo(bookmarkNo);

    if (bookmark == null) {
      out.println("번호가 유효하지 않습니다.");
      return;
    }

    out.printf("북마크 제목: %s\n", bookmark.getTitle());
    out.println("--------------------------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardDao.findAllByBookmarkNo(bookmarkNo);

    for (PhotoBoard photoBaord : photoBoards) {
      out.printf("%d, %s, %s, %d \n", //
          photoBaord.getNo(), //
          photoBaord.getTitle(), //
          photoBaord.getCreadtedDate(), //
          photoBaord.getViewCount());
    }
  }
}
