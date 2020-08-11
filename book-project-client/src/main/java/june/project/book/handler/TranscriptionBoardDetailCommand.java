package june.project.book.handler;

import java.util.List;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardDetailCommand implements Command {

  List<TranscriptionBoard> transcriptionBoardList;

  public Prompt prompt;

  public TranscriptionBoardDetailCommand(Prompt prompt, List<TranscriptionBoard> list) {
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

    TranscriptionBoard transcriptionBoard = this.transcriptionBoardList.get(index);
    System.out.printf("번호: %d\n", transcriptionBoard.getNo());
    System.out.printf("제목: %s\n", transcriptionBoard.getTitle());
    System.out.printf("도서명: %s\n", transcriptionBoard.getBookTitle());
    System.out.printf("지은이: %s\n", transcriptionBoard.getAuthor());
    System.out.printf("출판사: %s\n", transcriptionBoard.getPublisher());
    System.out.printf("필사 내용: %s\n", transcriptionBoard.getContent());
    System.out.printf("사진:%s\n", transcriptionBoard.getPhoto());
    System.out.printf("등록일:%s\n", transcriptionBoard.getDate());
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
