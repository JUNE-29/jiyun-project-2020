package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.util.Prompt;

public class BookBoardDeleteCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookBoardDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/book/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("도서 게시판의 정보를 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
