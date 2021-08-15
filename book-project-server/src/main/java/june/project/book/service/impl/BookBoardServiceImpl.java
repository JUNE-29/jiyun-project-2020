package june.project.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

@Component
public class BookBoardServiceImpl implements BookBoardService {

  BookBoardDao bookBoardDao;

  public BookBoardServiceImpl(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void add(BookBoard bookBoard) throws Exception {
    bookBoardDao.insert(bookBoard);
  }

  @Override
  public List<BookBoard> list() throws Exception {
    return bookBoardDao.findAll();
  }

  @Override
  public BookBoard get(int no) throws Exception {
    return bookBoardDao.findByBookNo(no);
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {
    return bookBoardDao.update(bookBoard);
  }

  @Override
  public int delete(int no) throws Exception {
    return bookBoardDao.delete(no);
  }

}
