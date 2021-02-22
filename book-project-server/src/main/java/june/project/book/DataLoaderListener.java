package june.project.book;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.BookBoardDao;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.MemberDao;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
import june.project.book.service.impl.BookBoardServiceImpl2;
import june.project.book.service.impl.BookmarkServiceImpl;
import june.project.book.service.impl.MemberServiceImpl;
import june.project.book.service.impl.PhotoBoardServiceImpl;
import june.project.sql.MybatisDaoFactory;
import june.project.sql.PlatformTransactionManager;
import june.project.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // Mybatis 객체 준비
      InputStream inputStream =
          Resources.getResourceAsStream("june/project/book/conf/mybatis-config.xml");

      // 트랜잭션 제어를 위해 오리지널 객체를 프록시 객체에 담아 사용한다.
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      context.put("sqlSessionFactory", sqlSessionFactory);

      // DAO 프록시 객체를 생성해 줄 Factory 준비
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      BookBoardDao bookBoardDao = daoFactory.createDao(BookBoardDao.class);
      BookmarkDao bookmarkDao = daoFactory.createDao(BookmarkDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

      // 트랜잭션 관리자 준비
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

      context.put("bookBoardService", new BookBoardServiceImpl2(sqlSessionFactory));
      context.put("bookmarkService", new BookmarkServiceImpl(bookmarkDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
