package com.eomcs.lms;

import java.util.Scanner;

public class LessonHandler {
  static Lesson[] lessons = new Lesson[100];
  static int lessonsSize = 0;
  static Scanner keyScan;
  
  static void addLesson() {

    Lesson lesson = new Lesson();

    lesson.no = Input.getIntValue("번호? ");
    lesson.title = Input.getStringValue("수업명? ");
    lesson.contents = Input.getStringValue("설명? ");
    lesson.startDate = Input.getDateValue("시작일? ");
    lesson.endDate = Input.getDateValue("종료일? ");
    lesson.totalHours = Input.getIntValue("총 수업시간? ");
    lesson.dayHours = Input.getIntValue("일 수업시간? ");

    lessons[lessonsSize++] = lesson;
    System.out.println("저장완료");
  }
  
  static void listLesson() {
    for (int i = 0; i < lessonsSize; i++) {
      Lesson lesson = lessons[i];
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.startDate,
          lesson.endDate, lesson.totalHours);
    }
  }
  

  
}
