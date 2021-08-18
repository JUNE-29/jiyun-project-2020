package june.project.book.service;

import java.util.List;
import june.project.book.domain.BookBasket;

public interface BookBasketService {

  int add(BookBasket bookBasket) throws Exception;

  List<BookBasket> list() throws Exception;

  BookBasket get(int no) throws Exception;

  int update(BookBasket bookBasket) throws Exception;

  int delete(int no) throws Exception;

}
