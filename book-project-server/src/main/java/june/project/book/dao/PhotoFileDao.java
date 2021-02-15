package june.project.book.dao;

import java.util.List;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;

public interface PhotoFileDao {

  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;

}
