package june.project.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import june.project.book.dao.BooksDao;
import june.project.book.domain.Books;
import june.project.book.service.BooksService;

@Component
public class BooksServiceImpl implements BooksService {

  BooksDao booksDao;

  public BooksServiceImpl(BooksDao booksDao) {
    this.booksDao = booksDao;
  }

  @Override
  public int add(Books books) throws Exception {
    return booksDao.insert(books);
  }

  @Override
  public Books get(int no) throws Exception {
    return booksDao.findByBookNo(no);
  }

  @Override
  public int update(Books books) throws Exception {
    return booksDao.updateBookBoardNo(books);
  }

  @Override
  public int delete(int no) throws Exception {
    return booksDao.delete(no);
  }

  @Override
  public List<Books> getBookboardNo1() throws Exception {
    return booksDao.BookboardNo1List();
  }

  @Override
  public List<Books> getBookboardNo2() throws Exception {
    return booksDao.BookboardNo2List();
  }

}
