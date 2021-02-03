package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardDaoImpl implements BookBoardDao {

  Connection con;

  public BookBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(BookBoard bookBoard) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("insert into book_board(titl, auth, pub, cate, pub_dt, conts,"
          + " photo, score, book_st) values('" //
          + bookBoard.getBookTitle() + "','" + bookBoard.getAuthor() + "','"
          + bookBoard.getPublisher() + "','" + bookBoard.getCategories() + "','"
          + bookBoard.getPublishedDate() + "','" + bookBoard.getContent() + "','"
          + bookBoard.getPhoto() + "','" + bookBoard.getScore() + "','" + bookBoard.getBookStatus()
          + "')");

      return result;
    }
  }

  @Override
  public List<BookBoard> findAll() throws Exception {

    try (Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select board_id, titl, score, cdt, book_st from book_board")) {
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
    try (Statement stmt = con.createStatement(); //
        ResultSet rs = stmt.executeQuery( //
            "select board_id, titl, auth, pub, cate, pub_dt, conts, photo, score, book_st, cdt "
                + " from book_board " + " where board_id= " + no)) {

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

  @Override
  public int update(BookBoard bookBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update book_board set" //
          + " titl = '" + bookBoard.getBookTitle() //
          + "', auth = '" + bookBoard.getAuthor() //
          + "', pub = '" + bookBoard.getPublisher() //
          + "', cate = '" + bookBoard.getCategories() //
          + "', pub_dt = '" + bookBoard.getPublishedDate() //
          + "', conts = '" + bookBoard.getContent() //
          + "', photo = '" + bookBoard.getPhoto() //
          + "', score = '" + bookBoard.getScore() //
          + "', book_st= '" + bookBoard.getBookStatus() //
          + "' where board_id =" + bookBoard.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from book_board where board_id=" + no);

      return result;
    }
  }
}
