package june.project.book;

import java.util.Map;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.mariadb.BookBoardDaoImpl;
import june.project.book.dao.mariadb.BookmarkDaoImpl;
import june.project.book.dao.mariadb.MemberDaoImpl;
import june.project.book.dao.mariadb.PhotoBoardDaoImpl;
import june.project.book.dao.mariadb.PhotoFileDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {

      String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
      String username = "study";
      String password = "1111";

      context.put("bookBoardDao", new BookBoardDaoImpl(jdbcUrl, username, password));
      context.put("bookmarkDao", new BookmarkDaoImpl(jdbcUrl, username, password));
      context.put("memberDao", new MemberDaoImpl(jdbcUrl, username, password));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, username, password));
      context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, username, password));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
