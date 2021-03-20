package june.project.book.service;

import java.util.List;
import june.project.book.domain.Member;

public interface MemberService {

  int add(Member member) throws Exception;

  List<Member> list() throws Exception;

  Member get(int no) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  List<Member> search(String keyword) throws Exception;

  Member findByEmailAndPassword(String email, String password) throws Exception;
}
