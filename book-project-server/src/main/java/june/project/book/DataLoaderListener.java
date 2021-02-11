package june.project.book;

import java.util.Map;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.mariadb.BookBoardDaoImpl;
import june.project.book.dao.mariadb.BookmarkDaoImpl;
import june.project.book.dao.mariadb.MemberDaoImpl;
import june.project.book.dao.mariadb.PhotoBoardDaoImpl;
import june.project.book.dao.mariadb.PhotoFileDaoImpl;
import june.project.sql.PlatformTransactionManager;
import june.project.util.DataSource;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
      String username = "study";
      String password = "1111";

      // Connection 팩토리 준비
      DataSource dataSource = new DataSource(jdbcUrl, username, password);
      context.put("dataSource", dataSource);

      context.put("bookBoardDao", new BookBoardDaoImpl(dataSource));
      context.put("bookmarkDao", new BookmarkDaoImpl(dataSource));
      context.put("memberDao", new MemberDaoImpl(dataSource));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
      context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

      // 트랜잭션 관리자 준비
      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("transactionManager", txManager);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
