package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {
  
  LessonDao lessonDao;
  
  public LessonServlet(LessonDao lessonDao) {
    
    this.lessonDao = lessonDao;
    
    // Servlet가 사용할 DAO를 직접 만들기 않고 외부에서 주입받아 사용한다.
    // 이렇게 의존하는 객체를 외부에서 주입받아 사용하는 방법을 (Dependency Injection (DI); 의존성 주입) 이라 부른다.
    // 그래야만 의존객체를 교체하기 쉽다.
  }

  
  @Override
  public void service(String command, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    switch (command) {
      case "/lesson/add":
        addLesson(in, out);
        break;
      case "/lesson/list":
        listLesson(in, out);
        break;
      case "/lesson/delete":
        deleteLesson(in, out);
        break;
      case "/lesson/detail":
        detailLesson(in, out);
        break;
      case "/lesson/update":
        updateLesson(in, out);
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

  private void addLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    if(lessonDao.insert(lesson) == 0) {
      fail("강의정보를 입력할 수 없습니다.", out);
      return;
    }
    out.writeUTF("ok");
  }

  private void listLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonDao.findAll());
  }

  private void detailLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Lesson lesson = lessonDao.findBy(no);
    if(lesson == null) {
      fail("해당 번호의 강의정보가 없습니다.", out);
      return;
    }
    
    out.writeUTF("ok");
    out.writeObject(lesson);

  }

  private void updateLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    
    if(lessonDao.update(lesson) == 0) {
      fail("해당 번호의 강의정보가 없습니다.", out);
      return;
    }

    out.writeUTF("ok");
  }

  private void deleteLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (lessonDao.delete(no) == -1) {
      fail("해당 번호의 강의정보이 없습니다.", out);
      return;
    }

    out.writeUTF("ok");

  }

  private void fail(String cause, ObjectOutputStream out) throws IOException {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
