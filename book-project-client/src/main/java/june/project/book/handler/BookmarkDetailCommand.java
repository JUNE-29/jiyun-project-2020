package june.project.book.handler;

import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkDetailCommand implements Command {

  BookmarkDao bookmarkDao;
  public Prompt prompt;

  public BookmarkDetailCommand(BookmarkDao bookmarkDao, Prompt prompt) {
    this.bookmarkDao = bookmarkDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      Bookmark bookmark = bookmarkDao.findByNo(no);

      System.out.printf("번호: %d\n", bookmark.getNo());
      System.out.printf("제목: %s\n", bookmark.getTitle());
      System.out.printf("도서명: %s\n", bookmark.getBookTitle());
      System.out.printf("지은이: %s\n", bookmark.getAuthor());
      System.out.printf("출판사: %s\n", bookmark.getPublisher());
      System.out.printf("필사 내용: %s\n", bookmark.getContent());
      System.out.printf("사진:%s\n", bookmark.getPhoto());
      System.out.printf("등록일:%s\n", bookmark.getDate());

    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
