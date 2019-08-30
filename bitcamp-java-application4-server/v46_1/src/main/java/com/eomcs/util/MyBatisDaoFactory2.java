package com.eomcs.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MyBatisDaoFactory2 {
  SqlSessionFactory sqlSessionFactory;

  public MyBatisDaoFactory2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @SuppressWarnings("unchecked")
  public <T> T createDao(Class<T> clazz) {
    return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz},
        new InvocationHandler() {

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
          String interfaceName = clazz.getName();
          String methodName = method.getName();
          String sqlId = interfaceName + "." + methodName;

          Class<?> returnType = method.getReturnType();

          if (returnType == List.class) {
            return (args == null) ? sqlSession.selectList(sqlId)
                : sqlSession.selectList(sqlId, args[0]);
          } else if (returnType == int.class || returnType == void.class) {
            return (args == null) ? sqlSession.insert(sqlId)
                : sqlSession.insert(sqlId, args[0]);
          } else {
            return (args == null) ? sqlSession.selectOne(sqlId)
                : sqlSession.selectOne(sqlId, args[0]);
          }

        }
      }
    });
  }
}
