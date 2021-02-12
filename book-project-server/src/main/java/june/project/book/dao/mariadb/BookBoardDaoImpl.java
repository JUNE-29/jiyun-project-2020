package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.DataSource;

public class BookBoardDaoImpl implements BookBoardDao {

  DataSource dataSource;

  public BookBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(BookBoard bookBoard) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "insert into book_board(titl, auth, pub, cate, pub_dt, conts," //
                + " photo, score, book_st) values(?,?,?,?,?,?,?,?,?)")) {

      stmt.setString(1, bookBoard.getBookTitle());
      stmt.setString(2, bookBoard.getAuthor());
      stmt.setString(3, bookBoard.getPublisher());
      stmt.setString(4, bookBoard.getCategories());
      stmt.setString(5, bookBoard.getPublishedDate());
      stmt.setString(6, bookBoard.getContent());
      stmt.setString(7, bookBoard.getPhoto());
      stmt.setInt(8, bookBoard.getScore());
      stmt.setInt(9, bookBoard.getBookStatus());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<BookBoard> findAll() throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select board_id, titl, score, cdt, book_st" //
                + " from book_board" //
                + " order by board_id desc"); //
        ResultSet rs = stmt.executeQuery()) {
      ArrayList<BookBoard> list = new ArrayList<>();

      while (rs.next()) { // 데이터를 가져왔다면 true 리턴

        BookBoard bookBoard = new BookBoard(); // 새로운 BookBoard 메모리 준비
        bookBoard.setNo(rs.getInt("board_id"));
        bookBoard.setBookTitle(rs.getString("titl"));
        bookBoard.setScore(rs.getInt("score"));
        bookBoard.setDate(rs.getDate("cdt"));
        bookBoard.setBookStatus(rs.getInt("book_st"));
        list.add(bookBoard);
      }
      return list;
    }
  }

  @Override
  public BookBoard findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "select board_id, titl, auth, pub, cate, pub_dt, conts," //
                + " photo, score, book_st, cdt" //
                + " from book_board" //
                + " where board_id=?" //
        )) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {

          BookBoard bookBoard = new BookBoard();
          bookBoard.setNo(rs.getInt("board_id"));
          bookBoard.setBookTitle(rs.getString("titl"));
          bookBoard.setAuthor(rs.getString("auth"));
          bookBoard.setPublisher(rs.getString("pub"));
          bookBoard.setCategories(rs.getString("cate"));
          bookBoard.setPublishedDate(rs.getString("pub_dt"));
          bookBoard.setContent(rs.getString("conts"));
          bookBoard.setPhoto(rs.getString("photo"));
          bookBoard.setScore(rs.getInt("score"));
          bookBoard.setBookStatus(rs.getInt("book_st"));
          bookBoard.setDate(rs.getDate("cdt"));
          return bookBoard;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(BookBoard bookBoard) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "update book_board set" //
                + " titl=?, auth=?, pub=?, cate=?, pub_dt=?, conts=?, photo=?," //
                + " score=?, book_st=?" //
                + " where board_id =?")) {

      stmt.setString(1, bookBoard.getBookTitle());
      stmt.setString(2, bookBoard.getAuthor());
      stmt.setString(3, bookBoard.getPublisher());
      stmt.setString(4, bookBoard.getCategories());
      stmt.setString(5, bookBoard.getPublishedDate());
      stmt.setString(6, bookBoard.getContent());
      stmt.setString(7, bookBoard.getPhoto());
      stmt.setInt(8, bookBoard.getScore());
      stmt.setInt(9, bookBoard.getBookStatus());
      stmt.setInt(10, bookBoard.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement( //
            "delete from book_board where board_id=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }
}
