package june.project.book.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkDaoImpl implements BookmarkDao {

  SqlSessionFactory sqlSessionFactory;

  public BookmarkDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Bookmark bookmark) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("BookmarkMapper.insertBookmark", bookmark);
      return count;
    }
  }

  @Override
  public List<Bookmark> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BookmarkMapper.selectBookmark");
    }
  }

  @Override
  public Bookmark findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BookmarkMapper.selectDetail", no);
    }
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("BookmarkMapper.updateBookmark", bookmark);
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("BookmarkMapper.deleteBookmark", no);
      return count;
    }
  }

  @Override
  public List<Bookmark> findByKeyword(Map<String, Object> params) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BookmarkMapper.selectBookmark", params);
    }
  }
}
