package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookBoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      out.writeUTF("/book/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      BookBoard review = (BookBoard) in.readObject();
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

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
