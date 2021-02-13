package june.project.book.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardDaoImpl implements BookBoardDao {

  SqlSessionFactory sqlSessionFactory;

  public BookBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(BookBoard bookBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("BookBoardMapper.insertBookBoard", bookBoard);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<BookBoard> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BookBoardMapper.selectBookBoard");
    }
  }

  @Override
  public BookBoard findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BookBoardMapper.selectDetail", no);
    }
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("BookBoardMapper.updateBookBoard", bookBoard);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("deleteBookBoard", no);
      sqlSession.commit();
      return count;
    }
  }
}
