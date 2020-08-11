package june.project.book.handler;

import java.util.List;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardDetailCommand implements Command {

  List<BookBoard> bookBoardList;

  Prompt prompt;

  public BookBoardDetailCommand(Prompt prompt, List<BookBoard> list) {
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

    BookBoard review = this.bookBoardList.get(index);

    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("도서명: %s\n", review.getBookTitle());
    System.out.printf("지은이: %s\n", review.getAuthor());
    System.out.printf("출판사: %s\n", review.getPublisher());
    System.out.printf("카테고리: %s\n", review.getCategories());
    System.out.printf("내용: %s\n", review.getContent());
    System.out.printf("이미지: %s\n", review.getPhoto());
    System.out.printf("평가: %1.1f점\n", review.getScore());
    System.out.printf("진행 상태: %s\n", review.getBookStatus());
    System.out.printf("등록일: %s\n", review.getDate());
    System.out.printf("조회수: %s\n", review.getViewCount());
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
