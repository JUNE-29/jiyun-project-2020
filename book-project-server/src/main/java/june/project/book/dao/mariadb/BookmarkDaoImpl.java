package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;
import june.project.util.DataSource;

public class BookmarkDaoImpl implements BookmarkDao {

  DataSource dataSource;

  public BookmarkDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public int insert(Bookmark bookmark) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "insert into bookmark(titl, book_titl, auth, pub, conts, photo)"
                + "values(?,?,?,?,?,?)")) {

      stmt.setString(1, bookmark.getTitle());
      stmt.setString(2, bookmark.getBookTitle());
      stmt.setString(3, bookmark.getAuthor());
      stmt.setString(4, bookmark.getPublisher());
      stmt.setString(5, bookmark.getContent());
      stmt.setString(6, bookmark.getPhoto());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Bookmark> findAll() throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select bookmark_id, titl, book_titl, auth, cdt from bookmark"); //
        ResultSet rs = stmt.executeQuery()) {
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
  public Bookmark findByNo(int no) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select bookmark_id, titl, book_titl, auth, pub, conts, photo, cdt" + " from bookmark" //
                + " where bookmark_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {

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
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "update bookmark set" //
                + " titl=?, book_titl=?, auth=?, pub=?, conts=?, photo=?" //
                + " where bookmark_id=?")) {

      stmt.setString(1, bookmark.getTitle());
      stmt.setString(2, bookmark.getBookTitle());
      stmt.setString(3, bookmark.getAuthor());
      stmt.setString(4, bookmark.getPublisher());
      stmt.setString(5, bookmark.getContent());
      stmt.setString(6, bookmark.getPhoto());
      stmt.setInt(7, bookmark.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "delete from bookmark where bookmark_id=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }
}
