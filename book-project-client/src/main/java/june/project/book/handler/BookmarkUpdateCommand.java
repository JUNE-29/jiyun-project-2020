package june.project.book.handler;

import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkUpdateCommand implements Command {

  BookmarkDao bookmarkDao;
  public Prompt prompt;

  public BookmarkUpdateCommand(BookmarkDao bookmarkDao, Prompt prompt) {
    this.bookmarkDao = bookmarkDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      Bookmark oldBookmark = null;

      try {
        oldBookmark = bookmarkDao.findByNo(no);

      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      Bookmark newBookmark = new Bookmark();

      newBookmark.setNo(oldBookmark.getNo());

      newBookmark.setTitle(prompt.inputString( //
          String.format("제목(%s)? ", oldBookmark.getTitle()), oldBookmark.getTitle()));

      newBookmark.setBookTitle(prompt.inputString( //
          String.format("도서명(%s)?", oldBookmark.getBookTitle()), oldBookmark.getBookTitle()));

      newBookmark.setAuthor(prompt.inputString( //
          String.format("지은이(%s)? ", oldBookmark.getAuthor()), oldBookmark.getAuthor()));

      newBookmark.setPublisher(prompt.inputString( //
          String.format("출판사(%s)? ", oldBookmark.getPublisher()), oldBookmark.getPublisher()));

      newBookmark.setContent(prompt.inputString( //
          String.format("내용(%s)? ", oldBookmark.getContent()), oldBookmark.getContent()));

      newBookmark.setPhoto( //
          prompt.inputString(String.format("이미지(%s)? ", oldBookmark.getPhoto()),
              oldBookmark.getPhoto()));

      bookmarkDao.update(newBookmark);
      System.out.println("북마크 정보 변경했습니다.");

    } catch (Exception e) {
      System.out.println("변경 실패!");
      e.printStackTrace();
    }
  }
}
