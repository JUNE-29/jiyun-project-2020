package june.project.book.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;

public class BookBoardServiceImpl2 implements BookBoardService {

  SqlSessionFactory sqlSessionFactory;

  public BookBoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(BookBoard bookBoard) throws Exception {
    // 스레드 마다 SqlSession 객체를 구분해서 사용하기 때문에
    // 메서드가 호출될 때 마다 SqlSession 객체를 생성해야 한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      // SqlSession 객체를 사용하여 BookBoardDao 인터페이스의 구현체를 얻는다.
      BookBoardDao bookBoardDao = sqlSession.getMapper(BookBoardDao.class);
      bookBoardDao.insert(bookBoard);
    }
  }

  @Override
  public List<BookBoard> list() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BookBoardDao bookBoardDao = sqlSession.getMapper(BookBoardDao.class);
      return bookBoardDao.findAll();
    }
  }

  @Override
  public BookBoard get(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BookBoardDao bookBoardDao = sqlSession.getMapper(BookBoardDao.class);
      return bookBoardDao.findByNo(no);
    }
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BookBoardDao bookBoardDao = sqlSession.getMapper(BookBoardDao.class);
      return bookBoardDao.update(bookBoard);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BookBoardDao bookBoardDao = sqlSession.getMapper(BookBoardDao.class);
      return bookBoardDao.delete(no);
    }
  }
}
