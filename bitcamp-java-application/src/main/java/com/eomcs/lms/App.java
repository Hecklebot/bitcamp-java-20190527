// 애플리케이션 메인 클래스
// 애플리케이션을 실행할 때 이 클래스를 실행한다.
// 어케바꾸냐 바꿔줘

package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.lms.util.Input;

public class App {

  
  static Scanner keyScan;
  
  public static void main(String[] args) {

    keyScan = new Scanner(System.in);
    
    LessonHandler.keyScan = keyScan;
    MemberHandler.keyScan = keyScan;
    BoardHandler.keyScan = keyScan;
    Input.keyScan = keyScan;
    
    BoardHandler boardHandler = new BoardHandler();
    LessonHandler lessonHandler = new LessonHandler();
    MemberHandler memberHandler = new MemberHandler();
    
    while (true) {
      String command = prompt(); //짧은 메소드를 만드는 이유: 코드를 설명하기 수월해진다.
      if(command.equals("quit")) {
        System.out.println("시스템 종료");
        break;
      } else if (command.equals("/lesson/add")) { //자바에는 else if 문법은 없다 else{}(생략) if{}
        lessonHandler.addLesson(); //addLesson() 메서드 블록에 묶어놓은 코드를 실행한다.
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
      } else if (command.equals("/board2/add")) {
        boardHandler.addBoard();
      } else if (command.equals("/board2/list")) {
        boardHandler.listBoard();
      } else {
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
