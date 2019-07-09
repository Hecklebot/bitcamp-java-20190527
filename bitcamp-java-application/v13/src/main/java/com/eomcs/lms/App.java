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
    
    LessonHandler lessonHandler = new LessonHandler();
    MemberHandler memberHandler = new MemberHandler();
    BoardHandler boardHandler = new BoardHandler(); //heap영역에 static이 붙지 않은 instance field를 load함
    BoardHandler boardHandler2 = new BoardHandler();
    
    while (true) {
      String command = Input.prompt();
      if (command.equals("quit")) {
        break;
      } else if (command.equals("/lesson/add")) {
        lessonHandler.addLesson();
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
      } else if (command.equals("/board/add")) {
        boardHandler.addBoard(); //BoardHandler class의 addBoard를 boardHandler의 인스턴스를 사용해 실행
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();
      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();
      } else {
        System.out.println("해당 명령은 지원하지 않습니다.");
      }
      System.out.println();
    }
  }

  







}
