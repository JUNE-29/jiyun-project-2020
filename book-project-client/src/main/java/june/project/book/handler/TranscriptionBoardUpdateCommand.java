package june.project.book.handler;

import java.sql.Date;
import java.util.List;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardUpdateCommand implements Command {

  List<TranscriptionBoard> transcriptionBoardList;

  public Prompt prompt;

  public TranscriptionBoardUpdateCommand(Prompt prompt, List<TranscriptionBoard> list) {
    this.prompt = prompt;
    this.transcriptionBoardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfReading(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("필사 게시판의 번호가 유효하지 않습니다.");
      return;
    }


    TranscriptionBoard oldTranscription = this.transcriptionBoardList.get(index);
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

    this.transcriptionBoardList.set(index, newTranscription);
    System.out.println("필사 게시판의 정보 변경했습니다.");
  }

  private int indexOfReading(int no) {
    for (int i = 0; i < this.transcriptionBoardList.size(); i++) {
      if (this.transcriptionBoardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
