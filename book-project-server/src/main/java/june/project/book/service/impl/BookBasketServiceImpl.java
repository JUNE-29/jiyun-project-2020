package june.project.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;
import june.project.book.service.BookBasketService;

@Component
public class BookBasketServiceImpl implements BookBasketService {

  BookBasketDao bookBasketDao;

  public BookBasketServiceImpl(BookBasketDao bookBasketDao) {
    this.bookBasketDao = bookBasketDao;
  }

  @Override
  public int add(BookBasket bookBasket) throws Exception {
    return bookBasketDao.insert(bookBasket);
  }

  @Override
  public List<BookBasket> list() throws Exception {
    return bookBasketDao.findAll();
  }

  @Override
  public BookBasket get(int no) throws Exception {
    return bookBasketDao.findByBasketBookNo(no);
  }

  @Override
  public int update(BookBasket bookBasket) throws Exception {
    return bookBasketDao.update(bookBasket);
  }

  @Override
  public int delete(int no) throws Exception {
    return bookBasketDao.delete(no);
  }

}
