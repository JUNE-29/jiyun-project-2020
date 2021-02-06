package june.project.book.dao;

import java.util.List;
import june.project.book.domain.PhotoBoard;

public interface PhotoBoardDao {

  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAllByBookmarkNo(int bookmarkNo) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;

}
