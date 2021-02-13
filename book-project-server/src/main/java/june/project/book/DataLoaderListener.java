package june.project.book;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

      // Mybatis 객체 준비
      InputStream inputStream =
          Resources.getResourceAsStream("june/project/book/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      context.put("bookBoardDao", new BookBoardDaoImpl(sqlSessionFactory));
      context.put("bookmarkDao", new BookmarkDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

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
