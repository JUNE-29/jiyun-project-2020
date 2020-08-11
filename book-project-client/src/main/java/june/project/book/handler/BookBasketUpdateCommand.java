package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BookBasketUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {


    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/basket/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      BookBasket oldBasket = (BookBasket) in.readObject();
      BookBasket newBasket = new BookBasket();

      newBasket.setNo(oldBasket.getNo());

      newBasket.setBookTitle(prompt.inputString( //
          String.format("도서명(%s)?", oldBasket.getBookTitle()), oldBasket.getBookTitle()));

      newBasket.setAuthor(prompt.inputString( //
          String.format("지은이(%s)? ", oldBasket.getAuthor()), oldBasket.getAuthor()));

      newBasket.setPublisher(prompt.inputString( //
          String.format("출판사(%s)? ", oldBasket.getPublisher()), oldBasket.getPublisher()));

      newBasket.setPublishedDate( //
          prompt.inputString(String.format("출판 연도(%s)? ", oldBasket.getPublishedDate()),
              oldBasket.getPublishedDate()));

      newBasket.setCategories( //
          prompt.inputString(String.format("카테고리(%s)? ", oldBasket.getCategories()),
              oldBasket.getCategories()));

      newBasket.setMemo( //
          prompt.inputString(String.format("즐겨 찾기 한 이유(%s)? ", oldBasket.getMemo()),
              oldBasket.getMemo()));

      if (oldBasket.equals(newBasket)) {
        System.out.println("즐겨찾는 도서의 변경을 취소했습니다.");
        return;
      }

      out.writeUTF("/basket/update");
      out.writeObject(newBasket);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("즐겨찾는 도서를 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
