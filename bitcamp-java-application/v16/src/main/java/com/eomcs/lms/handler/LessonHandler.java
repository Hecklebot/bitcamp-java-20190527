package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.Input;

public class LessonHandler {
  
  private Input input;
  
  public LessonHandler(Input input) {
    this.input = input;
  }
  
  private LessonList lessonList = new LessonList();
  
  
  public void addLesson() {
  
    Lesson lesson = new Lesson();

    lesson.setNo(input.getIntValue("번호? "));
    lesson.setTitle(input.getStringValue("수업명? "));
    lesson.setContents(input.getStringValue("설명? "));
    lesson.setStartDate(input.getDateValue("시작일? "));
    lesson.setEndDate(input.getDateValue("종료일? "));
    lesson.setTotalHours(input.getIntValue("총 수업시간? "));
    lesson.setDayHours(input.getIntValue("일 수업시간? "));
    
    //LessonHandler에서 직접 데이터를 보관하지 않고, LessonList에 데이터를 전달한다.
    lessonList.add(lesson);
    System.out.println("저장완료");
  }
  
  public void listLesson() {
    
    Lesson[] lessons = lessonList.toArray();
    for (Lesson lesson : lessons) {
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }
  

  
}
