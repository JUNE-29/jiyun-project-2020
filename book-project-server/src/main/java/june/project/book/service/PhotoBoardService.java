package june.project.book.service;

import java.util.List;
import june.project.book.domain.PhotoBoard;

public interface PhotoBoardService {

  void add(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> listBookmarkPhoto(int boardNo) throws Exception;

  PhotoBoard get(int no) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;

  void delete(int no) throws Exception;
}
