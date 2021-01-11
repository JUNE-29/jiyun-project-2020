package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookmarkAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
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
      out.writeUTF("/bookmark/add");
      out.writeObject(bookmark);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF()); // 서버의 메시지(왜 실패했는지)를 읽는다.
        return;
      }

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
