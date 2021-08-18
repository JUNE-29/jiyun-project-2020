package june.project.book.service;

import java.util.List;
import june.project.book.domain.Books;

public interface BooksService {

  int add(Books books) throws Exception;

  Books get(int no) throws Exception;

  List<Books> getBookboardNo1() throws Exception;

  List<Books> getBookboardNo2() throws Exception;

  int update(Books books) throws Exception;

  int delete(int no) throws Exception;

}