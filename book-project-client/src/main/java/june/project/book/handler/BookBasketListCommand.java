package june.project.book.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import june.project.book.domain.BookBasket;
import june.project.util.Prompt;

public class BookBasketListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public BookBasketListCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/basket/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<BookBasket> bookBasket = (List<BookBasket>) in.readObject();
      for (BookBasket basket : bookBasket) {
        System.out.printf("%d, %s, %s\n", //
            basket.getNo(), basket.getBookTitle(), basket.getCategories());
      }
    } catch (Exception e) {

    }

  }
}
