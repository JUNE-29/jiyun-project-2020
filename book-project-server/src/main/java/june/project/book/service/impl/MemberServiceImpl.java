package june.project.book.service.impl;

import java.util.HashMap;
import java.util.List;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.Component;

@Component
public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  @Override
  public int delete(int no) throws Exception {
    return memberDao.delete(no);
  }

  @Override
  public List<Member> getKeyword(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    HashMap<String, Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);
    return memberDao.findByEmailAndPassword(params);
  }

}
