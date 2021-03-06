package june.project.book;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import june.project.book.dao.BookBoardDao;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.MemberDao;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
import june.project.sql.MybatisDaoFactory;
import june.project.sql.PlatformTransactionManager;
import june.project.sql.SqlSessionFactoryProxy;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 그 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "june.project.book")
public class AppConfig {


  public AppConfig() throws Exception {}

  // Spring IoC 컨테이너에 수동으로 객체를 등록하고 싶다면,
  // 그 객체를 만들어 주는 팩토리 메서드를 정의해야 한다.
  // => 단 메서드 선언부에 @Bean 애노테이션을 붙여야 한다.
  // => 그래야만 Spring IoC 컨테이너는
  // 이 메서드를 호출하고 그 리턴 값을 보관한다.

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    // MyBatis 설정파일을 로딩할 때 사용할 입력스트림 준비
    InputStream inputStream =
        Resources.getResourceAsStream("june/project/book/conf/mybatis-config.xml");

    // 트랜잭션 제어를 위해 오리지널 객체를 프록시 객체에 담아 사용한다.
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public MybatisDaoFactory mybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {
    // 이 메서드를 호출할 때 Spring IoC 컨테이너에 들어 있는 객체를 원한다면,
    // 이렇게 파라미터로 선언하라.
    // 그러면 Spring IoC 컨테이너가 이 팩토리 메서드를 호출하기 전에
    // SqlSessionFactory를 먼저 준비한 다음에 이 메서드를 실행할 것이다.

    // DAO 프록시 객체를 생성해 줄 Factory를 준비
    return new MybatisDaoFactory(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager transactionManager(SqlSessionFactory sqlSessionFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public BookBoardDao bookBoardDao(MybatisDaoFactory daoFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return daoFactory.createDao(BookBoardDao.class);
  }

  @Bean
  public BookmarkDao bookmarkDao(MybatisDaoFactory daoFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return daoFactory.createDao(BookmarkDao.class);
  }

  @Bean
  public MemberDao memberDao(MybatisDaoFactory daoFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라
    // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
    return daoFactory.createDao(PhotoFileDao.class);
  }
}
