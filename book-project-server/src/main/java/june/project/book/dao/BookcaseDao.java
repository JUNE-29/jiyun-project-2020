package june.project.book.dao;

import java.util.List;
import june.project.book.domain.Bookcase;

public interface BookcaseDao {

  public int insert(Bookcase bookcase) throws Exception;

  public List<Bookcase> findAll() throws Exception;

  public Bookcase findByReadBookNo(int no) throws Exception;

  public int updateStarScore(Bookcase bookcase) throws Exception;

  public int delete(int no) throws Exception;
}
