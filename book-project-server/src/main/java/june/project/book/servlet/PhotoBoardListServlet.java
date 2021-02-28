package june.project.book.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.Component;
import june.project.util.Prompt;

@Component("/photoboard/list")
public class PhotoBoardListServlet implements Servlet {

  PhotoBoardService photoBoardService;
  BookmarkService bookmarkService;


  public PhotoBoardListServlet(PhotoBoardService photoBoardService,
      BookmarkService bookmarkService) {
    this.photoBoardService = photoBoardService;
    this.bookmarkService = bookmarkService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int bookmarkNo = Prompt.getInt(in, out, "북마크 번호? ");

    Bookmark bookmark = bookmarkService.get(bookmarkNo);

    if (bookmark == null) {
      out.println("번호가 유효하지 않습니다.");
      return;
    }

    out.printf("북마크 제목: %s\n", bookmark.getTitle());
    out.println("--------------------------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardService.listBookmarkPhoto(bookmarkNo);

    for (PhotoBoard photoBaord : photoBoards) {
      out.printf("%d, %s, %s, %d \n", //
          photoBaord.getNo(), //
          photoBaord.getTitle(), //
          photoBaord.getCreadtedDate(), //
          photoBaord.getViewCount());
    }
  }
}
