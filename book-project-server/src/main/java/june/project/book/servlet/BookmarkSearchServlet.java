package june.project.book.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class BookmarkSearchServlet {

  BookmarkService bookmarkService;

  public BookmarkSearchServlet(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/bookmark/search")
  public void service(Scanner in, PrintStream out) throws Exception {

    HashMap<String, Object> params = new HashMap<>();

    String keyword = Prompt.getString(in, out, "제목 검색: ");
    if (keyword.length() > 0) {
      params.put("title", keyword);
    }

    String bookTitle = Prompt.getString(in, out, "책 제목 검색: ");
    if (bookTitle.length() > 0) {
      params.put("bookTitle", bookTitle);
    }

    String author = Prompt.getString(in, out, "지은이 검색: ");
    if (author.length() > 0) {
      params.put("author", author);
    }

    Date date = Prompt.getDate(in, out, "글쓴 날짜 검색: ");
    if (date != null) {
      params.put("date", date);
    }

    out.println("--------------------------------------------------");
    out.println("[검색 결과]");
    out.println();

    List<Bookmark> bookmarks = bookmarkService.findByKeyword(params);
    for (Bookmark b : bookmarks) {
      out.printf("%d, %s, %s, %s, %s\n", //
          b.getNo(), b.getTitle(), b.getBookTitle(), b.getAuthor(), b.getDate());
    }
  }
}

