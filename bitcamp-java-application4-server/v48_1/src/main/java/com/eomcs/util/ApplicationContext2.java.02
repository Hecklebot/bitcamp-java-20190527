package com.eomcs.util;

import java.io.InputStream;
import java.util.HashMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.LoginCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberSearchCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.lms.handler.PhotoBoardAddCommand;
import com.eomcs.lms.handler.PhotoBoardDeleteCommand;
import com.eomcs.lms.handler.PhotoBoardDetailCommand;
import com.eomcs.lms.handler.PhotoBoardListCommand;
import com.eomcs.lms.handler.PhotoBoardUpdateCommand;

// 자바 객체를 자동 생성하여 관리하는 역할
public class ApplicationContext2 {
  
  HashMap<String,Object> objPool = new HashMap<>();
  
  public ApplicationContext2() throws Exception {
    // MyBatis 객체 준비
    InputStream inputStream =
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));

    PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

    // DAO 구현체 생성기 준비
    MyBatisDaoFactory daoFactory = new MyBatisDaoFactory(sqlSessionFactory);

    // BoardDao boardDao = daoFactory.createDao(BoardDao.class);
    MemberDao memberDao = daoFactory.createDao(MemberDao.class);
    LessonDao lessonDao = daoFactory.createDao(LessonDao.class);
    PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
    PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

    objPool.put("/lesson/add", new LessonAddCommand(lessonDao));
    objPool.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
    objPool.put("/lesson/detail", new LessonDetailCommand(lessonDao));
    objPool.put("/lesson/list", new LessonListCommand(lessonDao));
    objPool.put("/lesson/update", new LessonUpdateCommand(lessonDao));

    objPool.put("/member/add", new MemberAddCommand(memberDao));
    objPool.put("/member/delete", new MemberDeleteCommand(memberDao));
    objPool.put("/member/detail", new MemberDetailCommand(memberDao));
    objPool.put("/member/list", new MemberListCommand(memberDao));
    objPool.put("/member/update", new MemberUpdateCommand(memberDao));
    objPool.put("/member/search", new MemberSearchCommand(memberDao));

    objPool.put("/board/add", new BoardAddCommand(sqlSessionFactory));
    objPool.put("/board/delete", new BoardDeleteCommand(sqlSessionFactory));
    objPool.put("/board/detail", new BoardDetailCommand(sqlSessionFactory));
    objPool.put("/board/list", new BoardListCommand(sqlSessionFactory));
    objPool.put("/board/update", new BoardUpdateCommand(sqlSessionFactory));

    objPool.put("/photoboard/add",
        new PhotoBoardAddCommand(photoBoardDao, photoFileDao, txManager));
    objPool.put("/photoboard/delete",
        new PhotoBoardDeleteCommand(photoBoardDao, photoFileDao, txManager));
    objPool.put("/photoboard/detail", new PhotoBoardDetailCommand(photoBoardDao));
    objPool.put("/photoboard/list", new PhotoBoardListCommand(photoBoardDao));
    objPool.put("/photoboard/update",
        new PhotoBoardUpdateCommand(photoBoardDao, photoFileDao, txManager));

    objPool.put("/auth/login", new LoginCommand(memberDao));
  }

  public Object getBean(String name) throws RuntimeException {
    
    Object obj = objPool.get(name);
    if(obj == null) {
      throw new RuntimeException("해당 이름의 Bean을 찾을 수 없습니다.");
    }
    
    return null;
  }

  public void addBean(String name, Object obj) {
    if(name == null || obj == null) {
      return;
    }
    
    objPool.put(name, obj);
    
  }


}
