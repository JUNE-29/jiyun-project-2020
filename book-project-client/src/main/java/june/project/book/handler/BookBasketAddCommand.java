package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public BookBasketAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    BookBasket bookBasket = new BookBasket();

    bookBasket.setNo(prompt.inputInt("번호? "));

    bookBasket.setBookTitle(prompt.inputString("도서명? "));

    bookBasket.setAuthor(prompt.inputString("지은이? "));

    bookBasket.setPublisher(prompt.inputString("출판사? "));

    bookBasket.setCategories(prompt.inputString("카테고리? "));

    bookBasket.setPublishedDate(prompt.inputString("출판 연도? "));

    bookBasket.setMemo(prompt.inputString("즐겨찾기 한 이유? "));

    try {
      out.writeUTF("/basket/add");
      out.writeObject(bookBasket);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }

  }
}
