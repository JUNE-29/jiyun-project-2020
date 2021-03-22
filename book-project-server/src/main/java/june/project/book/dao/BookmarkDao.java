package june.project.book.dao;

import java.util.List;
import java.util.Map;
import june.project.book.domain.Bookmark;

public interface BookmarkDao {

  public List<Bookmark> findAll() throws Exception;

  public int insert(Bookmark bookmark) throws Exception;

  public Bookmark findByNo(int no) throws Exception;

  public int update(Bookmark bookmark) throws Exception;

  public int delete(int no) throws Exception;

  List<Bookmark> findByKeyword(Map<String, Object> params) throws Exception;
}
