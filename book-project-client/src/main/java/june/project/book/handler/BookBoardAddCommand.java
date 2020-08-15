package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  public Prompt prompt;

  public BookBoardAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    BookBoard bookBoard = new BookBoard();

    bookBoard.setNo(prompt.inputInt("번호? "));
    bookBoard.setBookTitle(prompt.inputString("도서명? "));
    bookBoard.setAuthor(prompt.inputString("지은이? "));
    bookBoard.setPublisher(prompt.inputString("출판사? "));
    bookBoard.setCategories(prompt.inputString("카테고리? "));
    bookBoard.setPublishedDate(prompt.inputString("출판 연도? "));
    bookBoard.setContent(prompt.inputString("내용? "));
    bookBoard.setPhoto(prompt.inputString("이미지? "));
    bookBoard.setBookStatus(prompt.inputString("진행 상태? "));
    bookBoard.setScore(prompt.inputFloat("책에 대한 점수(5.0점만점)? "));
    bookBoard.setDate(new Date(System.currentTimeMillis()));
    bookBoard.setViewCount(0);

    try {
      out.writeUTF("/book/add");
      out.writeObject(bookBoard);
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
