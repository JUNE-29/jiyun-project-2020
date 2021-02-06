package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목? ");
    out.println("!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("북마크 번호? ");
    out.println("!{}!");
    out.flush();

    Bookmark bookmark = new Bookmark();
    bookmark.setNo(Integer.parseInt(in.nextLine()));

    photoBoard.setBookmark(bookmark);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("새 게시글을 등록했습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
