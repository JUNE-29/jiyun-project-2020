package june.project.book.service;

import java.util.List;
import june.project.book.domain.Bookcase;

public interface BookcaseService {

  int add(Bookcase bookcase) throws Exception;

  List<Bookcase> list() throws Exception;

  Bookcase get(int no) throws Exception;

  int update(Bookcase bookcase) throws Exception;

  int delete(int no) throws Exception;

}
