package june.project.book.dao;

import java.util.List;
import june.project.book.domain.PhotoFile;

public interface PhotoFileDao {

  int insert(PhotoFile photofile) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;

}
