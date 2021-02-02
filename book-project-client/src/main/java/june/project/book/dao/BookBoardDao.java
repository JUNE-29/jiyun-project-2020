package june.project.book.dao;

import java.util.List;
import june.project.book.domain.BookBoard;

public interface BookBoardDao {

  public int insert(BookBoard bookBoard) throws Exception;

  public List<BookBoard> findAll() throws Exception;

  public BookBoard findByNo(int no) throws Exception;

  public int update(BookBoard bookBoard) throws Exception;

  public int delete(int no) throws Exception;

}
