package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Date;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonUpdateCommand implements Command {

  private LessonDao lessonDao;

  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      lesson.setTitle(Input.getStringValue(in, out, "수업명(" + lesson.getTitle() + ")? "));

      lesson.setContents(Input.getStringValue(in, out, "수업내용? "));

      lesson.setStartDate(
          Date.valueOf(Input.getStringValue(in, out, "시작일(" + lesson.getStartDate() + ")? ")));

      lesson.setEndDate(
          Date.valueOf(Input.getStringValue(in, out, "종료일(" + lesson.getEndDate() + ")? ")));

      lesson.setContents(Input.getStringValue(in, out, "총수업시간(" + lesson.getTotalHours() + ")? "));

      lesson.setContents(Input.getStringValue(in, out, "일수업시간(" + lesson.getDayHours() + ")? "));

      lessonDao.update(lesson);
      out.println("데이터를 변경하였습니다.");
    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}


