package june.project.book;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Spring IoC 컨테이너가 이 클래스를 Java Config로 자동 인식하게 하려면
// 다음 태그를 붙여야 한다.
// 단 이 클래스가 @ComponentScan에서 지정한 패키지 안에 있어야 한다.
@Configuration

// 다음 애노테이션을 선언하면
// @Transactional 이 붙은 메서드가 있을 경우
// 트랜잭션 제어 코드가 삽입된 프록시 객체를 자동생성 한다.
@EnableTransactionManagement

// Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩한다.
@PropertySource("classpath:june/project/book/conf/jdbc.properties")

public class DatabaseConfig {

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

  public DatabaseConfig() {
    System.out.println("DatabaseConfig 객체 생성!");
  }

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
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
