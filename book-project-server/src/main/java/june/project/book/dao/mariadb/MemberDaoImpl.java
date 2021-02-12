package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;
import june.project.util.DataSource;

public class MemberDaoImpl implements MemberDao {

  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Member> findAll() throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select member_id, name, email, cdt from book_member"); //
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {

        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setRegisteredDate(rs.getDate("cdt"));
        list.add(member);
      }
      return list;
    }
  }

  @Override
  public int insert(Member member) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement(//
            "insert into book_member(name, email, pwd, photo) " //
                + "values(?,?,password(?),?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoto());

      return stmt.executeUpdate();
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select member_id, name, email, pwd, photo, cdt" //
                + " from book_member" //
                + " where member_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {

          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setPhoto(rs.getString("photo"));
          member.setRegisteredDate(rs.getDate("cdt"));
          return member;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "update book_member set" //
                + " name=?, email=?, pwd=password(?), photo=?" //
                + " where member_id=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoto());
      stmt.setInt(5, member.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "delete from book_member where member_id=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select member_id, name, email, cdt" //
                + " from book_member" //
                + " where name like ?" //
                + " or email like ?" //
        )) {

      String value = "%" + keyword + "%";

      stmt.setString(1, value);
      stmt.setString(2, value);

      try (ResultSet rs = stmt.executeQuery()) {
        ArrayList<Member> list = new ArrayList<>();

        while (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setRegisteredDate(rs.getDate("cdt"));
          list.add(member);
        }
        return list;
      }
    }
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select member_id, name, email, pwd, photo" //
                + " from book_member" //
                + " where email=? and pwd=password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {

          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setPhoto(rs.getString("photo"));
          return member;

        } else {
          return null;
        }
      }
    }
  }
}
