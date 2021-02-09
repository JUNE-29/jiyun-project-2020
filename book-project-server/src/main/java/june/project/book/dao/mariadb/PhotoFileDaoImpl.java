package june.project.book.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import june.project.book.dao.PhotoFileDao;
import june.project.book.domain.PhotoFile;
import june.project.util.ConnectionFactory;

public class PhotoFileDaoImpl implements PhotoFileDao {

  ConnectionFactory conFactory;

  public PhotoFileDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {

    try (Connection con = conFactory.getConnection(); //
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate( //
          "insert into book_photo_file(photo_id, file_path) values(" //
              + photoFile.getBoardNo() + ", '" + photoFile.getFilePath() //
              + "')");

      return result;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {

    try (Connection con = conFactory.getConnection(); //
        Statement stmt = con.createStatement(); //

        ResultSet rs = stmt.executeQuery("select photo_file_id, photo_id, file_path" //
            + " from book_photo_file" + " where photo_id=" + boardNo //
            + " order by photo_file_id asc")) {

      ArrayList<PhotoFile> list = new ArrayList<>();

      while (rs.next()) {

        // 1) 생정자를 통해 인스턴스 필드의 값을 설정하기
        // PhotoFile photoFile = new PhotoFile();
        // rs.getInt("photo_file_id");
        // rs.getString("file_path");
        // rs.getInt("photo_id");
        // list.add(photoFile);

        // list.add(new PhotoFile( //
        // rs.getInt("photo_file_id"), //
        // rs.getString("file_path"), //
        // rs.getInt("photo_id")));

        // 2) 셋터를 통해 체인 방식으로 인스턴스 필드의 값을 설정
        list.add(new PhotoFile() //
            .setNo(rs.getInt("photo_file_id")) //
            .setFilePath(rs.getString("file_path")) //
            .setBoardNo(rs.getInt("photo_id")));

      }
      return list;
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {

    try (Connection con = conFactory.getConnection(); //
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate( //
          "delete from book_photo_file" + " where photo_id=" + boardNo);
      return result;
    }
  }
}
