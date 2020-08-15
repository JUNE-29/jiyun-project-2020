package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public TranscriptionBoardUpdateCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/transcription/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      TranscriptionBoard oldTranscription = (TranscriptionBoard) in.readObject();
      TranscriptionBoard newTranscription = new TranscriptionBoard();

      newTranscription.setNo(oldTranscription.getNo());

      newTranscription.setTitle(prompt.inputString( //
          String.format("제목(%s)? ", oldTranscription.getTitle()), oldTranscription.getTitle()));

      newTranscription.setBookTitle(prompt.inputString( //
          String.format("도서명(%s)?", oldTranscription.getBookTitle()),
          oldTranscription.getBookTitle()));

      newTranscription.setAuthor(prompt.inputString( //
          String.format("지은이(%s)? ", oldTranscription.getAuthor()), oldTranscription.getAuthor()));

      newTranscription.setPublisher(prompt.inputString( //
          String.format("출판사(%s)? ", oldTranscription.getPublisher()),
          oldTranscription.getPublisher()));

      newTranscription.setContent(prompt.inputString( //
          String.format("필사 내용(%s)? ", oldTranscription.getContent()),
          oldTranscription.getContent()));

      newTranscription.setPhoto( //
          prompt.inputString(String.format("이미지(%s)? ", oldTranscription.getPhoto()),
              oldTranscription.getPhoto()));

      newTranscription.setDate(new Date(System.currentTimeMillis()));

      if (oldTranscription.equals(newTranscription)) {
        System.out.println("필사 게시판의 정보 변경을 취소했습니다.");
        return;
      }

      out.writeUTF("/transcription/update");
      out.writeObject(newTranscription);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("필사 게시판의 정보 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
