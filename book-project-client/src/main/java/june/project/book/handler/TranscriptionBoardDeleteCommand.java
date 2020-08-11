package june.project.book.handler;

import java.util.List;
import june.project.book.domain.TranscriptionBoard;
import june.project.util.Prompt;

public class TranscriptionBoardDeleteCommand implements Command {

  List<TranscriptionBoard> transcriptionBoardList;

  public Prompt prompt;

  public TranscriptionBoardDeleteCommand(Prompt prompt, List<TranscriptionBoard> list) {
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

    this.transcriptionBoardList.remove(index);
    System.out.println("필사 게시판의 정보를 삭제했습니다.");
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
