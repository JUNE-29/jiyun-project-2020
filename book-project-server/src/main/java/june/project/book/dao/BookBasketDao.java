package june.project.book.dao;

import java.util.List;
import june.project.book.domain.BookBasket;

public interface BookBasketDao {

  public int insert(BookBasket bookBasket) throws Exception;

  public List<BookBasket> findAll() throws Exception;

  public BookBasket findByBasketBookNo(int no) throws Exception;

  public int update(BookBasket bookBasket) throws Exception;

  public int delete(int no) throws Exception;
}
