package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public TranscriptionBoardAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    TranscriptionBoard transcriptionBoard = new TranscriptionBoard();

    transcriptionBoard.setNo(prompt.inputInt("번호? "));
    transcriptionBoard.setTitle(prompt.inputString("제목? "));
    transcriptionBoard.setBookTitle(prompt.inputString("도서명? "));
    transcriptionBoard.setAuthor(prompt.inputString("지은이? "));
    transcriptionBoard.setPublisher(prompt.inputString("출판사? "));
    transcriptionBoard.setContent(prompt.inputString("필사 내용? "));
    transcriptionBoard.setPhoto(prompt.inputString("이미지? "));
    transcriptionBoard.setDate(new Date(System.currentTimeMillis()));

    try {
      out.writeUTF("/transcription/add");
      out.writeObject(transcriptionBoard);
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
