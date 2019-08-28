package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Board board) throws Exception {
    // 여기 SqlSession은 openSession()해서 리턴받은 오토커밋 켜진 SqlSession
    //  -> photoboardAdd에 이는 SqlSession과 다름
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("BoardDao.insert", board);
      return count;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardDao.findAll");
    }
  }

  @Override
  public Board findBy(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Board board = sqlSession.selectOne("BoardDao.findBy", no);

      if (board != null) {
        sqlSession.update("BoardDao.increaseViewCount", no);
      }
      return board;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    // openSession()을 호출할 때, 다음과 같이 boolean값을 넣으면 autoCommit 여부를 설정할 수 있다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("BoardDao.update", board);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.delete("BoardDao.delete", no);
    }
  }

}
