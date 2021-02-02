package june.project.book.handler;

import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardDetailCommand implements Command {

  Prompt prompt;
  BookBoardDao bookBoardDao;

  public BookBoardDetailCommand(BookBoardDao bookBoardDao, Prompt prompt) {
    this.bookBoardDao = bookBoardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      BookBoard review = bookBoardDao.findByNo(no);
      System.out.printf("번호: %d\n", review.getNo());
      System.out.printf("도서명: %s\n", review.getBookTitle());
      System.out.printf("지은이: %s\n", review.getAuthor());
      System.out.printf("출판사: %s\n", review.getPublisher());
      System.out.printf("카테고리: %s\n", review.getCategories());
      System.out.printf("내용: %s\n", review.getContent());
      System.out.printf("이미지: %s\n", review.getPhoto());
      System.out.printf("평가: %d점\n", review.getScore());
      System.out.printf("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): %d\n", review.getBookStatus());
      System.out.printf("등록일: %s\n", review.getDate());

    } catch (Exception e) {
      System.out.println("조회 실패!");
      e.printStackTrace();
    }
  }
}
