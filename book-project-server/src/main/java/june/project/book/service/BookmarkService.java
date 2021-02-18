package june.project.book.service;

import java.util.HashMap;
import java.util.List;
import june.project.book.domain.Bookmark;

public interface BookmarkService {

  int add(Bookmark bookmark) throws Exception;

  Bookmark get(int no) throws Exception;

  int update(Bookmark bookmark) throws Exception;

  int delete(int no) throws Exception;

  List<Bookmark> list() throws Exception;

  List<Bookmark> findByKeyword(HashMap<String, Object> params) throws Exception;

}
