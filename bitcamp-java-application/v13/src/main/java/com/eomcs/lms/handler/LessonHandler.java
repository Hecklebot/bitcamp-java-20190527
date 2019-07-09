package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
  
  private Lesson[] lessons = new Lesson[100];
  private int lessonsSize = 0;

  public static Scanner keyScan;
  
  public void listLesson() {
    for (int i = 0; i < lessonsSize; i++) {
      Lesson lesson = lessons[i];
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.startDate,
          lesson.endDate, lesson.totalHours);
    }
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    lesson.no = getIntValue("번호? ");
    lesson.title = getStringValue("수업명? ");
    lesson.startDate = getDateValue("시작일? ");
    lesson.endDate = getDateValue("종료일? ");
    lesson.totalHours = getIntValue("총 수업시간? ");
    lesson.dayHours = getIntValue("일 수업시간? ");

    lessons[lessonsSize++] = lesson;

    System.out.println("수업 저장완료");
  }

  

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-01-01과 같이 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

}
