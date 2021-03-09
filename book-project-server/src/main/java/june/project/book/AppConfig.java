package june.project.book;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 그 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "june.project.book")

// Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩한다.
@PropertySource("classpath:june/project/book/conf/jdbc.properties")

// Mybatis DAO 프록시를 자동생성 할 인터페이스를 지정한다.
@MapperScan("june.project.book.dao")

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
  public SqlSessionFactory sqlSessionFactory( //
      DataSource dataSource, // DB커넥션풀 필요
      ApplicationContext appCtx // Spring IoC 컨테이너 필요
  ) throws Exception {

    // Spring IoC 컨테이너 용으로 mybatis 측에서 따로 제작한 SqlSessionFactory이다.
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("june.project.book.domain");
    sqlSessionFactoryBean.setMapperLocations( //
        // Spring Ioc 컨테이너를 통해 SQL 맵퍼 파일의 위치 정보를 가져온다.
        appCtx.getResources("classpath:june/project/book/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
