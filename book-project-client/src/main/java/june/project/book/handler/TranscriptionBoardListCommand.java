package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.TranscriptionBoard;

public class TranscriptionBoardListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public TranscriptionBoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {
      out.writeUTF("/transcription/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<TranscriptionBoard> transcription = (List<TranscriptionBoard>) in.readObject();
      for (TranscriptionBoard t : transcription) {
        System.out.printf("%d, %s, %s, %s, %s\n", t.getNo(), t.getTitle(), t.getBookTitle(),
            t.getAuthor(), t.getDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
