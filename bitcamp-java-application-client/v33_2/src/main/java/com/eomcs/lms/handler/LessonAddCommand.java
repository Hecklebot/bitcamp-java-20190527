package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonAddCommand implements Command {
  
  private LessonDao lessonDao;
  private Input input;
  
  public LessonAddCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute() {
    
    Lesson lesson = new Lesson();
    
    lesson.setNo(input.getIntValue("번호? "));
    lesson.setTitle(input.getStringValue("수업명? "));
    lesson.setContents(input.getStringValue("설명? "));
    lesson.setStartDate(input.getDateValue("시작일? "));
    lesson.setEndDate(input.getDateValue("종료일? "));
    lesson.setTotalHours(input.getIntValue("총수업시간? "));
    lesson.setDayHours(input.getIntValue("일수업시간? "));
    
    try {
      lessonDao.insert(lesson);
      System.out.println("저장하였습니다.");
    } catch(Exception e) {
      System.out.println("데이터 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}












