package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookmarkUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/bookmark/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Bookmark oldBookmark = (Bookmark) in.readObject();
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

      newBookmark.setDate(new Date(System.currentTimeMillis()));

      if (oldBookmark.equals(newBookmark)) {
        System.out.println("북마크 정보 변경을 취소했습니다.");
        return;
      }

      out.writeUTF("/bookmark/update");
      out.writeObject(newBookmark);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("북마크 정보 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
