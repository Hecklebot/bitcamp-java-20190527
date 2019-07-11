package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.Input;

public class LessonHandler {
  
  private Lesson[] lessons = new Lesson[100];
  private int lessonsSize = 0;
  private Input input;
  
  public LessonHandler(Input input) {
    this.input = input;
  }
  
  
  public void addLesson() {

    Lesson lesson = new Lesson();

    lesson.no = input.getIntValue("번호? ");
    lesson.title = input.getStringValue("수업명? ");
    lesson.contents = input.getStringValue("설명? ");
    lesson.startDate = input.getDateValue("시작일? ");
    lesson.endDate = input.getDateValue("종료일? ");
    lesson.totalHours = input.getIntValue("총 수업시간? ");
    lesson.dayHours = input.getIntValue("일 수업시간? ");

    lessons[lessonsSize++] = lesson;
    System.out.println("저장완료");
  }
  
  public void listLesson() {
    for (int i = 0; i < lessonsSize; i++) {
      Lesson lesson = lessons[i];
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.startDate,
          lesson.endDate, lesson.totalHours);
    }
  }
  

  
}
