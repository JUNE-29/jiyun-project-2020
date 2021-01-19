package june.project.book.dao;

import java.util.List;
import june.project.book.domain.Member;

public interface MemberDao {

  public List<Member> findAll() throws Exception;

  public int insert(Member member) throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;
}
