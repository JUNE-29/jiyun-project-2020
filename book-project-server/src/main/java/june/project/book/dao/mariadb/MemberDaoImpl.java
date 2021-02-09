package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.MemberDao;
import june.project.book.domain.Member;

public class MemberDaoImpl implements MemberDao {

  String jdbcUrl;
  String username;
  String password;

  public MemberDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  @Override
  public List<Member> findAll() throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select member_id, name, email, cdt from book_member")) {
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

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("insert into book_member(name, email, pwd, photo) values('" //
          + member.getName() + "','" + member.getEmail() + "', '" + member.getPassword() + "','"
          + member.getPhoto() + "')");

      return result;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select member_id, name, email, pwd, cdt, photo " + "from book_member"
                + " where member_id=" + no)) {

      while (rs.next()) {
        Member member = new Member();

        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setPhoto(rs.getString("photo"));

        return member;
      }
      return null;
    }
  }

  @Override
  public int update(Member member) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update book_member set" //
          + " name = '" + member.getName() //
          + "', email = '" + member.getEmail() //
          + "', pwd = '" + member.getPassword() //
          + "', photo = '" + member.getPhoto() //
          + "' where member_id=" + member.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from book_member where member_id=" + no);
      return result;
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select member_id, name, email, cdt" //
            + " from book_member" //
            + " where name like '%" + keyword //
            + "%' or email like '%" + keyword + "%'")) {

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
