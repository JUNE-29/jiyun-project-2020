package june.project.book.handler;

import june.project.book.dao.BookmarkDao;
import june.project.util.Prompt;

public class BookmarkDeleteCommand implements Command {

  BookmarkDao bookmarkDao;
  public Prompt prompt;

  public BookmarkDeleteCommand(BookmarkDao bookmarkDao, Prompt prompt) {
    this.bookmarkDao = bookmarkDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");

      if (bookmarkDao.delete(no) > 0) {
        System.out.println("북마크 정보를 삭제했습니다.");

      } else {
        System.out.println("해당 번호의 게시글이 없습니다.");
      }

    } catch (Exception e) {

      System.out.println("삭제 실패!");
    }
  }
}
