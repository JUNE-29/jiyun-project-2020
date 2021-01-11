package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.domain.Bookmark;
import june.project.util.Prompt;

public class BookmarkDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookmarkDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Bookmark bookmark = (Bookmark) in.readObject();
      System.out.printf("번호: %d\n", bookmark.getNo());
      System.out.printf("제목: %s\n", bookmark.getTitle());
      System.out.printf("도서명: %s\n", bookmark.getBookTitle());
      System.out.printf("지은이: %s\n", bookmark.getAuthor());
      System.out.printf("출판사: %s\n", bookmark.getPublisher());
      System.out.printf("필사 내용: %s\n", bookmark.getContent());
      System.out.printf("사진:%s\n", bookmark.getPhoto());
      System.out.printf("등록일:%s\n", bookmark.getDate());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
