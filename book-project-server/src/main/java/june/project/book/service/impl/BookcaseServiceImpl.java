package june.project.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import june.project.book.dao.BookcaseDao;
import june.project.book.domain.Bookcase;
import june.project.book.service.BookcaseService;

@Component
public class BookcaseServiceImpl implements BookcaseService {

  BookcaseDao bookcaseDao;

  public BookcaseServiceImpl(BookcaseDao bookcaseDao) {
    this.bookcaseDao = bookcaseDao;
  }

  @Override
  public int add(Bookcase bookcase) throws Exception {
    return bookcaseDao.insert(bookcase);
  }

  @Override
  public List<Bookcase> list() throws Exception {
    return bookcaseDao.findAll();
  }

  @Override
  public Bookcase get(int no) throws Exception {
    return bookcaseDao.findByReadBookNo(no);
  }

  @Override
  public int update(Bookcase bookcase) throws Exception {
    return bookcaseDao.updateStarScore(bookcase);
  }

  @Override
  public int delete(int no) throws Exception {
    return bookcaseDao.delete(no);
  }



}
