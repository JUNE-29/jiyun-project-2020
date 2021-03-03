package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.Component;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class BookmarkAddServlet {

  BookmarkService bookmarkService;

  public BookmarkAddServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/add")
  public void service(Scanner in, PrintStream out) throws Exception {

    Bookmark bookmark = new Bookmark();

    bookmark.setTitle(Prompt.getString(in, out, "게시글 제목? "));
    bookmark.setBookTitle(Prompt.getString(in, out, "도서명? "));
    bookmark.setAuthor(Prompt.getString(in, out, "지은이? "));
    bookmark.setPublisher(Prompt.getString(in, out, "출판사? "));
    bookmark.setContent(Prompt.getString(in, out, "내용? "));
    bookmark.setPhoto(Prompt.getString(in, out, "이미지? "));

    if (bookmarkService.add(bookmark) > 0) {
      out.println("저장하였습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
