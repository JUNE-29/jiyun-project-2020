package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkDaoImpl implements BookmarkDao {

  String jdbcUrl;
  String username;
  String password;

  public BookmarkDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  @Override
  public List<Bookmark> findAll() throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select bookmark_id, titl, book_titl, auth, cdt from bookmark")) {
      ArrayList<Bookmark> list = new ArrayList<>();

      while (rs.next()) {
        Bookmark bookmark = new Bookmark();

        bookmark.setNo(rs.getInt("bookmark_id"));
        bookmark.setTitle(rs.getString("titl"));
        bookmark.setBookTitle(rs.getString("book_titl"));
        bookmark.setAuthor(rs.getString("auth"));
        bookmark.setDate(rs.getDate("cdt"));

        list.add(bookmark);
      }
      return list;
    }
  }

  @Override
  public int insert(Bookmark bookmark) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt
          .executeUpdate("insert into bookmark(titl, book_titl, auth, pub, conts, photo) values('" //
              + bookmark.getTitle() + "', '" + bookmark.getBookTitle() + "', '"
              + bookmark.getAuthor() + "', '" + bookmark.getPublisher() + "', '"
              + bookmark.getContent() + "', '" + bookmark.getPhoto() + "')");

      return result;
    }
  }

  @Override
  public Bookmark findByNo(int no) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select bookmark_id, titl, book_titl, auth, pub, conts, photo, cdt" + " from bookmark"
                + " where bookmark_id=" + no)) {

      if (rs.next()) {
        Bookmark bookmark = new Bookmark();

        bookmark.setNo(rs.getInt("bookmark_id"));
        bookmark.setTitle(rs.getString("titl"));
        bookmark.setBookTitle(rs.getString("book_titl"));
        bookmark.setAuthor(rs.getString("auth"));
        bookmark.setPublisher(rs.getString("pub"));
        bookmark.setContent(rs.getString("conts"));
        bookmark.setPhoto(rs.getString("photo"));
        bookmark.setDate(rs.getDate("cdt"));

        return bookmark;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update bookmark set " + "titl = '" + bookmark.getTitle() //
          + "', book_titl = '" + bookmark.getBookTitle() //
          + "', auth = '" + bookmark.getAuthor() //
          + "', pub = '" + bookmark.getPublisher() //
          + "', conts = '" + bookmark.getContent() //
          + "', photo = '" + bookmark.getPhoto() //
          + "' where bookmark_id =" + bookmark.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from bookmark where bookmark_id=" + no);

      return result;
    }
  }
}
