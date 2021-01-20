package june.project.book.handler;

import java.sql.Date;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkAddCommand implements Command {

  BookmarkDao bookmarkDao;
  public Prompt prompt;

  public BookmarkAddCommand(BookmarkDao bookmarkDao, Prompt prompt) {
    this.bookmarkDao = bookmarkDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Bookmark bookmark = new Bookmark();

    bookmark.setNo(prompt.inputInt("번호? "));
    bookmark.setTitle(prompt.inputString("제목? "));
    bookmark.setBookTitle(prompt.inputString("도서명? "));
    bookmark.setAuthor(prompt.inputString("지은이? "));
    bookmark.setPublisher(prompt.inputString("출판사? "));
    bookmark.setContent(prompt.inputString("내용? "));
    bookmark.setPhoto(prompt.inputString("이미지? "));
    bookmark.setDate(new Date(System.currentTimeMillis()));

    try {

      bookmarkDao.insert(bookmark);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("저장 실패!");
    }
  }
}
