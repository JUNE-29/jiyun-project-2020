package june.project.book.dao;

import java.util.List;
import june.project.book.domain.BookBasket;

// 데이터를 저장하고 꺼내는 방식(파일, 클라우드저장소, DB(데이터베이스) 등)에
// 상관없이 DAO 사용법을 통일하기 위해 메서드 호출 규칙을 정의한다.

public interface BookBasketDao {

  public List<BookBasket> findAll() throws Exception;

  public int insert(BookBasket bookBasket) throws Exception;

  public BookBasket findByNo(int no) throws Exception;

  public int update(BookBasket bookBasket) throws Exception;

  public int delete(int no) throws Exception;

}
