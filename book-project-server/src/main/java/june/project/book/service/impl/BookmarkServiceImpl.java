package june.project.book.service.impl;

import java.util.HashMap;
import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;
import june.project.book.service.BookmarkService;

public class BookmarkServiceImpl implements BookmarkService {

  BookmarkDao bookmarkDao;

  public BookmarkServiceImpl(BookmarkDao bookmarkDao) {
    this.bookmarkDao = bookmarkDao;
  }

  @Override
  public int add(Bookmark bookmark) throws Exception {
    return bookmarkDao.insert(bookmark);
  }

  @Override
  public List<Bookmark> list() throws Exception {
    return bookmarkDao.findAll();
  }

  @Override
  public Bookmark get(int no) throws Exception {
    return bookmarkDao.findByNo(no);
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {
    return bookmarkDao.update(bookmark);
  }

  @Override
  public int delete(int no) throws Exception {
    return bookmarkDao.delete(no);
  }


  @Override
  public List<Bookmark> findByKeyword(HashMap<String, Object> params) throws Exception {
    return bookmarkDao.findByKeyword(params);
  }

}