package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.Prompt;

public class BookmarkUpdateServlet implements Servlet {

  BookmarkService bookmarkService;

  public BookmarkUpdateServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Bookmark old = bookmarkService.get(no);
    if (old == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Bookmark bookmark = new Bookmark();

    bookmark.setNo(no);

    bookmark.setTitle(Prompt.getString(in, out, //
        String.format("제목(%s)?", old.getTitle())));

    bookmark.setBookTitle(Prompt.getString(in, out, //
        String.format("도서명(%s)?", old.getBookTitle())));

    bookmark.setAuthor(Prompt.getString(in, out, //
        String.format("지은이(%s)?", old.getAuthor())));

    bookmark.setPublisher(Prompt.getString(in, out, //
        String.format("출판사(%s)?", old.getPublisher())));

    bookmark.setContent(Prompt.getString(in, out, //
        String.format("내용(%s)?", old.getContent())));

    bookmark.setPhoto(Prompt.getString(in, out, //
        String.format("이미지(%s)?", old.getPhoto())));

    if (bookmarkService.update(bookmark) > 0) {
      out.println("변경했습니다.");

    } else {
      out.println("변경에 실패했습니다.");
    }
  }
}
