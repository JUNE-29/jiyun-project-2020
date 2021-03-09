package june.project.book;

import java.io.InputStream;
import javax.sql.DataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import june.project.sql.MybatisDaoFactory;
import june.project.sql.SqlSessionFactoryProxy;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 그 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "june.project.book")

// Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩한다.
@PropertySource("classpath:june/project/book/conf/jdbc.properties")

public class AppConfig {

  // @PropertySource로 로딩한 .properties 파일의 값을 사용하고 싶다면
  // 다음 애노테이션을 인스턴스 필드 앞에 붙여야 한다.
  // Spring IoC 컨테이너가 이 클래스의 객체를 생성할 때
  // 해당 필드에 프로퍼티 값을 자동으로 주입할 것이다.

  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

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
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
