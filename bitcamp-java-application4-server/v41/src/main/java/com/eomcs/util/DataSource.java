package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

// DAO가 사용할 커넥션 객체를 생성해주는 역할을 한다.
public class DataSource {

  String jdbcDriver;
  String jdbcUrl;
  String userName;
  String password;

  ThreadLocal<Connection> localConnection = new ThreadLocal<>();
  ArrayList<Connection> conPool = new ArrayList<>();

  public DataSource(String jdbcDriver, String jdbcUrl, String userName, String password) {
    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
    this.password = password;
    this.userName = userName;
  }


  public Connection getConnection() throws Exception {
    // ThreadLocal 도구를 사용하여 현재 스레드에서 커넥션 객체를 꺼낸다.
    Connection con = localConnection.get();

    if (con == null) {
      // 먼저 커넥션 풀에서 꺼낸다.
      if (conPool.size() > 0) {
        con = conPool.get(0);
        System.out.println("기존 커넥션 꺼내기 사용");
      } else {
        Class.forName(jdbcDriver);
        con = new TxConnection(DriverManager.getConnection(jdbcUrl, userName, password));
        System.out.println("새 커넥션을 만들어 사용");
      }
      localConnection.set(con);
    }
    return con;
  }

  // 현재 스레드에 보관된 커넥션 객체를 삭제한다.
  public void clearConnection() {
    Connection con = localConnection.get();
    if (con != null) {
      localConnection.remove();
      // 스레드에서 제거한 후, 커넥션 객체를 다시 커넥션 풀에 저장한다.
      conPool.add(con);
      System.out.println("커넥션 풀에 다시 저장");
    }
  }
}
