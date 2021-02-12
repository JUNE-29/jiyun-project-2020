package june.project.book.dao;

import java.util.List;
import june.project.book.domain.Member;

public interface MemberDao {

  List<Member> findAll() throws Exception;

  int insert(Member member) throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;

  default List<Member> findByKeyword(String keyword) throws Exception {
    return null;
  }

  default Member findByEmailAndPassword(String email, String password) throws Exception {
    return null;
  }
}
