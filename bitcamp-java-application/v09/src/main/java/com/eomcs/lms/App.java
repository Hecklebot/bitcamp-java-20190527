// 애플리케이션 메인 클래스
// 애플리케이션을 실행할 때 이 클래스를 실행한다.
// 어케바꾸냐 바꿔줘

package com.eomcs.lms;

import java.sql.Date;
import java.util.Random;
import java.util.Scanner;

public class App {

  static Scanner keyScan;
  static Random rdm;
  
  static Lesson[] lessons = new Lesson[100];
  static int lessonsSize = 0;
  static Member[] members = new Member[100];
  static int membersSize = 0;
  static Board[] boards = new Board[100];
  static int boardsSize =0;
  
  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    rdm = new Random();
    
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine();
      if(command.equals("quit")) {
        System.out.println("시스템 종료");
        break;
      } else if (command.equals("/lesson/add")) { //자바에는 else if 문법은 없다 else{}(생략) if{}
        addLesson(); //addLesson() 메서드 블록에 묶어놓은 코드를 실행한다.
      } else if (command.equals("/member/add")) {
        addMember();
      } else if (command.equals("/board/add")) {
        addBoard();
      } else if (command.equals("/lesson/list")) {
        listLesson();
      } else if(command.equals("/member/list")) {
        listMember();
      } else if (command.equals("/board/list")) {
        listBoard();
      }else {
        System.out.println("해당 명령은 지원하지 않습니다.");
      }
      System.out.println();
    }
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

  static void addLesson() {

    Lesson lesson = new Lesson();

    lesson.no = getIntValue("번호? ");
    lesson.title = getStringValue("수업명? ");
    lesson.contents = getStringValue("설명? ");
    lesson.startDate = getDateValue("시작일? ");
    lesson.endDate = getDateValue("종료일? ");
    lesson.totalHours = getIntValue("총 수업시간? ");
    lesson.dayHours = getIntValue("일 수업시간? ");

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

  static void addMember() {
    Member member = new Member();
    
    member.no = getIntValue("번호? ");
    member.name = getStringValue("이름? ");
    member.email = getStringValue("이메일? ");
    member.password = getStringValue("비밀번호? ");
    member.tel = getStringValue("전화번호? ");
    member.photo = getStringValue("사진? ");
    member.registeredDate = getDateValue("가입일? ");  

    members[membersSize++] = member;
    System.out.println("저장완료");
  }

  static void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.name, member.email, member.tel,  
         member.registeredDate);
    }
  }

  static void addBoard() {
    Board board = new Board();
    
    board.no = getIntValue("번호? ");
    board.contents = getStringValue("내용? ");
    board.createdDate = getDateValue("작성일? ");
    board.viewCount = rdm.nextInt(99);
    boards[boardsSize++] = board;
    System.out.println("저장완료");
  }

  static void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }

  
}
