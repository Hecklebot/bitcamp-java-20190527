package com.eomcs.lms.context;

import java.util.Map;
import com.eomcs.lms.dao.csv.BoardCsvDao;
import com.eomcs.lms.dao.csv.LessonCsvDao;
import com.eomcs.lms.dao.csv.MemberCsvDao;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

// 서버가 시작되거나 종료될 때 작업을 보고를 받고 작업을 수행하는 역할
// -> ServletContextListener 규칙을 준비해야만 시작과 종료 알림을 받을 수 있다.
public class AppInitListener implements ServletContextListener {

  BoardCsvDao boardDao;
  MemberCsvDao memberDao;
  LessonCsvDao lessonDao;

  @Override
  public void contextInitializer(Map<String, Object> context) {
    System.out.println("서버를 시작했으니 객체를 준비해야 겠다.");
    
    try {
    boardDao = new BoardCsvDao("./board.csv"); // DAO 객체를 준비하고
    } catch(Exception e) {
      System.out.println("게시물 로딩 중 오류발생");
    }
    
    try {
    memberDao = new MemberCsvDao("./member.csv");

    } catch(Exception e) {
      System.out.println("회원정보 로딩 중 오류발생");
    }

    try {
    lessonDao = new LessonCsvDao("./lesson.csv");

    } catch(Exception e) {
      System.out.println("수업정보 로딩 중 오류발생");
    }

    context.put("/board/", new BoardServlet(boardDao)); // Map에 집어넣는다.
    context.put("/member/", new MemberServlet(memberDao));
    context.put("/lesson/", new LessonServlet(lessonDao));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("서버가 종료됬으니 사용했던 자원을 해제해야 겠다.");
    boardDao.saveData();
    lessonDao.saveData();
    memberDao.saveData();
  }
}
