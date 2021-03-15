package june.project.book;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring IoC 컨테이너가 이 클래스를 Java Config로 자동 인식하게 하려면
// 다음 태그를 붙여야 한다.
// 단 이 클래스가 @ComponentScan에서 지정한 패키지 안에 있어야 한다.
@Configuration

// Mybatis DAO 프록시를 자동생성 할 인터페이스를 지정한다.
@MapperScan("june.project.book.dao")

public class MybatisConfig {

  static Logger logger = LogManager.getLogger(MybatisConfig.class);

  public MybatisConfig() {
    logger.debug("MybatisConfig 객체 생성!");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory( //
      DataSource dataSource, // DB커넥션풀 필요
      ApplicationContext appCtx // Spring IoC 컨테이너 필요
  ) throws Exception {

    // Mybatis의 log4j 활성화 시키기
    LogFactory.useLog4JLogging();

    // Spring IoC 컨테이너 용으로 mybatis 측에서 따로 제작한 SqlSessionFactory이다.
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("june.project.book.domain");
    sqlSessionFactoryBean.setMapperLocations( //
        // Spring Ioc 컨테이너를 통해 SQL 맵퍼 파일의 위치 정보를 가져온다.
        appCtx.getResources("classpath:june/project/book/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();
  }
}
