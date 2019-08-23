package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

// DAO가 사용할 커넥션 객체를 생성해주는 역할을 한다.
public class ConnectionFactory {
  
  String jdbcDriver;
  String jdbcUrl;
  String userName;
  String password;
  
  public ConnectionFactory(String jdbcDriver, String jdbcUrl, String userName, String password) {
    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
    this.password = password;
    this.userName = userName;
  }
  
  
  public Connection getConnection() throws Exception {
    Class.forName(jdbcDriver);
    // 커넥션 하나를 만들어서 리턴
    return DriverManager.getConnection(jdbcUrl, userName, password);
  }
}
