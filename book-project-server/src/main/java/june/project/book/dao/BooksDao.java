package june.project.book.dao;

import june.project.book.domain.Books;

public interface BooksDao {

  public int insert(Books books) throws Exception;

  public Books findByBookNo(int no) throws Exception;

  public int updateBookBoardNo(Books books) throws Exception;

  public int delete(int no) throws Exception;
}
