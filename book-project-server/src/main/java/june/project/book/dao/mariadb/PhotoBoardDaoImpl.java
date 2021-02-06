package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate("insert into book_photo(titl,bookmark_id) values('" //
          + photoBoard.getTitle() + "', " + photoBoard.getBookmark().getNo() //
          + ")");

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByBookmarkNo(int bookmarkNo) throws Exception {

    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select photo_id, titl, cdt, vw_cnt, bookmark_id" //
            + " from book_photo" + " where bookmark_id=" + bookmarkNo //
            + " order by photo_id desc")) {

      ArrayList<PhotoBoard> list = new ArrayList<>();

      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();

        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreadtedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        list.add(photoBoard);
      }
      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery("select" //
            + " p.photo_id," //
            + " p.titl," //
            + " p.cdt," //
            + " p.vw_cnt," //
            + " p.bookmark_id," //
            + " b.book_titl bookmark_title" //
            + " from book_photo p" //
            + " inner join bookmark b on p.bookmark_id = b.bookmark_id" //
            + " where photo_id =" + no)) {

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreadtedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        // 조인 결과 중에서 북마크 데이터를 Bookmark에 저장한다.
        Bookmark bookmark = new Bookmark();
        bookmark.setNo(rs.getInt("bookmark_id"));
        bookmark.setBookTitle(rs.getString("bookmark_title"));

        photoBoard.setBookmark(bookmark);

        return photoBoard;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update book_photo set titl='" //
          + photoBoard.getTitle() //
          + "' where photo_id=" + photoBoard.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(//
          "delete from book_photo" //
              + " where photo_id=" + no);

      return result;
    }
  }
}
