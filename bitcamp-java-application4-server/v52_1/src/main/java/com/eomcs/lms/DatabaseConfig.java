package com.eomcs.lms;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//<tx:annotation-driven/> 의 Java config 설정
// -> @Transactional 애노테이션을 처리할 객체를 등록한다.
@EnableTransactionManagement 
@PropertySource("classpath:com/eomcs/lms/conf/jdbc.properties")
public class DatabaseConfig {
  
  @Value("${jdbc.driver}") String jdbcDriver;
  @Value("${jdbc.url}") String jdbcUrl;
  @Value("${jdbc.username}") String jdbcUsername;
  @Value("${jdbc.password}") String jdbcPassword;
  
  // DB 커넥션풀 객체 준비
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(this.jdbcDriver); 
    ds.setUrl(this.jdbcUrl);
    ds.setUsername(this.jdbcUsername);
    ds.setPassword(this.jdbcPassword);
    return ds;
  }
  
  // 트랜잭션 관리자 준비
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}






