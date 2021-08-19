package june.project.book.service;

import java.util.HashMap;
import java.util.List;
import june.project.book.domain.Bookmark;

public interface BookmarkService {

  int add(Bookmark bookmark) throws Exception;

  List<Bookmark> list() throws Exception;

  Bookmark getBookcaseNo(int no) throws Exception;

  Bookmark getBookBasketNo(int no) throws Exception;

  int update(Bookmark bookmark) throws Exception;

  int delete(int no) throws Exception;

  List<Bookmark> search(HashMap<String, Object> params) throws Exception;

}
