package june.project.book.handler;

import java.util.List;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardDeleteCommand implements Command {

  List<BookBoard> bookBoardList;

  Prompt prompt;

  public BookBoardDeleteCommand(Prompt prompt, List<BookBoard> list) {
    this.prompt = prompt;
    this.bookBoardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecommendation(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("도서 게시판의 번호가 유효하지 않습니다.");
      return;
    }
    this.bookBoardList.remove(index);
    System.out.println("도서 게시판의 정보를 삭제했습니다.");
  }

  private int indexOfRecommendation(int no) {
    for (int i = 0; i < this.bookBoardList.size(); i++) {
      if (this.bookBoardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
