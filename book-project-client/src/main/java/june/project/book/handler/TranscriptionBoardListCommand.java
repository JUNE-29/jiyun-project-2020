package june.project.book.handler;

import java.util.Iterator;
import java.util.List;
import june.project.book.domain.TranscriptionBoard;

public class TranscriptionBoardListCommand implements Command {

  List<TranscriptionBoard> transcriptionBoardList;


  public TranscriptionBoardListCommand(List<TranscriptionBoard> list) {
    this.transcriptionBoardList = list;
  }

  @Override
  public void execute() {
    Iterator<TranscriptionBoard> iterator = transcriptionBoardList.iterator();
    while (iterator.hasNext()) {
      TranscriptionBoard t = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n", t.getNo(), t.getTitle(), t.getBookTitle(),
          t.getAuthor(), t.getDate());
    }
  }
}
