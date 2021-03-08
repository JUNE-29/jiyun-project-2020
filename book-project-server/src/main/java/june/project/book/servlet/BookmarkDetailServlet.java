package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class BookmarkDetailServlet {

  BookmarkService bookmarkService;

  public BookmarkDetailServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/detail")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Bookmark bookmark = bookmarkService.get(no);

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
