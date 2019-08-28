package com.eomcs.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

// MyBatis의 SqlSessionFactory 객체를 사용하여 트랜잭션을 관리하는 일을 한다.
public class PlatformTransactionManager {
  
  SqlSessionFactory sqlSessionFactory;
  
  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    // 생성자를 통해 넘어온 SqlSessionFactory는 SqlSessionFactoryProxy이다.
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  public void beginTransaction() throws Exception {
    // 여기에서 스레드가 사용할 SqlSession 객체를 미리 준비한다.
    ((SqlSessionFactoryProxy)sqlSessionFactory).prepareSessionInThread();
  }
  
  public void commit() throws Exception {
    // 현재 스레드의 주머니에서 SqlSession을 꺼낸다. 주머니에서 꺼낸 객체는 SqlSession이 아닌 SqlSessionProxy이다.
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.commit();
    ((SqlSessionProxy)sqlSession).realClose();
  }
  
  public void rollback() throws Exception {
    // 현재 스레드의 주머니에서 SqlSession을 꺼낸다. 주머니에서 꺼낸 객체는 SqlSession이 아닌 SqlSessionProxy이다.
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.rollback();
    ((SqlSessionProxy)sqlSession).realClose();
  }
  
  
}
