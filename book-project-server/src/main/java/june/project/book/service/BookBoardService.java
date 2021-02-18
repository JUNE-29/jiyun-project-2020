package june.project.book.service;

import java.util.List;
import june.project.book.domain.BookBoard;

public interface BookBoardService {

  int add(BookBoard bookBoard) throws Exception;

  List<BookBoard> list() throws Exception;

  BookBoard get(int no) throws Exception;

  int update(BookBoard bookBoard) throws Exception;

  int delete(int no) throws Exception;
}
