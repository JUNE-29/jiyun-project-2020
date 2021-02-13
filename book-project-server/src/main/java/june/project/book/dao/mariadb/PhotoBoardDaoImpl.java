package june.project.book.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("PhotoBoardMapper.insertPhotoBoard", photoBoard);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<PhotoBoard> findAllByBookmarkNo(int bookmarkNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoBoardMapper.selectPhotoBoard", bookmarkNo);
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("PhotoBoardMapper.selectDetail", no);
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("PhotoBoardMapper.updatePhotoBoard", photoBoard);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("PhotoBoardMapper.deletePhotoBoard", no);
      sqlSession.commit();
      return count;
    }
  }
}
