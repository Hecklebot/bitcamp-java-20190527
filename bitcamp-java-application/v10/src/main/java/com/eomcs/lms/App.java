// 애플리케이션 메인 클래스
// 애플리케이션을 실행할 때 이 클래스를 실행한다.
// 어케바꾸냐 바꿔줘

package com.eomcs.lms;

import java.util.Scanner;

public class App {

  
  static Scanner keyScan;
  static Board[] boards = new Board[100];
  static int boardsSize =0;
  
  public static void main(String[] args) {

    keyScan = new Scanner(System.in);
    
    LessonHandler.keyScan = keyScan;
    MemberHandler.keyScan = keyScan;
    BoardHandler.keyScan = keyScan;
    Input.keyScan = keyScan;
    
    while (true) {
      String command = prompt(); //짧은 메소드를 만드는 이유: 코드를 설명하기 수월해진다.
      if(command.equals("quit")) {
        System.out.println("시스템 종료");
        break;
      } else if (command.equals("/lesson/add")) { //자바에는 else if 문법은 없다 else{}(생략) if{}
        LessonHandler.addLesson(); //addLesson() 메서드 블록에 묶어놓은 코드를 실행한다.
      } else if (command.equals("/member/add")) {
        MemberHandler.addMember();
      } else if (command.equals("/board/add")) {
        BoardHandler.addBoard();
      } else if (command.equals("/lesson/list")) {
        LessonHandler.listLesson();
      } else if(command.equals("/member/list")) {
        MemberHandler.listMember();
      } else if (command.equals("/board/list")) {
        BoardHandler.listBoard();
      }else {
        System.out.println("해당 명령은 지원하지 않습니다.");
      }
      System.out.println();
    }
  }

//메소드 이름은 동사형 또는 동사로 시작한다.
  
  static String prompt() {
    System.out.print("명령> ");
    return keyScan.nextLine();
  }
}
