package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardAddCommand implements Command {

  List<TranscriptionBoard> transcriptionBoardList;

  public Prompt prompt;

  public TranscriptionBoardAddCommand(Prompt prompt, List<TranscriptionBoard> list) {
    this.prompt = prompt;
    this.transcriptionBoardList = list;
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

    this.transcriptionBoardList.add(transcriptionBoard);

    System.out.println("저장하였습니다.");
  }
}
