// 애플리케이션 메인 클래스
// 애플리케이션을 실행할 때 이 클래스를 실행한다.
// 어케바꾸냐 바꿔줘

package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Input;

public class App {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    keyScan = new Scanner(System.in);
    //Input 생성자를 통해 Input이 의존하는 객체인 Scanner를 주입한다.
    Input input = new Input(keyScan); //생성자의 파라미터는 그 인스턴스를 만드는데 필요한 필수값을 의미한다.
//  Input input2 = new Input(new Scanner(new FileInputStream("a.txt"))); //Input 클래스의 keyScan이 static이면 앞의 System.in은 사용할 수 없게된다.
    
    //각 핸들러의 생성자를 통해 의존객체 "Input"를 주입한다.
    //->이렇게 어떤 객체가 필요로 하는 의존 객체를 주입하는 것을 의존성 주입(dependency injection; DI)이라 한다.
    //->DI를 전문적으로 관리해주는 프레임워크가 있으니 그 이름도 유명한 Spring IoC 컨테이너
    BoardHandler boardHandler = new BoardHandler(input);
    BoardHandler boardHandler2 = new BoardHandler(input);
    LessonHandler lessonHandler = new LessonHandler(input);
    MemberHandler memberHandler = new MemberHandler(input);
    
    while (true) {
      String command = prompt(); //짧은 메소드를 만드는 이유: 코드를 설명하기 수월해진다.
      if(command.equals("quit")) { //나중에 switch case로 바꾸기
        System.out.println("시스템 종료");
        break;
      } else if (command.equals("/lesson/add")) { //자바에는 else if 문법은 없다 else{}(생략) if{}
        lessonHandler.addLesson(); //addLesson() 메서드 블록에 묶어놓은 코드를 실행한다.
        
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
        
      } else if (command.equals("/lesson/detail")) {
        lessonHandler.detailLesson();
        
      } else if (command.equals("/lesson/update")) {
        lessonHandler.updateLesson();
        
      } else if (command.equals("/lesson/delete")) {
        lessonHandler.deleteLesson();
        
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
        
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
        
      } else if (command.equals("/member/detail")) {
        memberHandler.detailMember();
        
      } else if (command.equals("/member/update")) {
        memberHandler.updateMember();
        
      } else if (command.equals("/member/delete")) {
        memberHandler.deleteMember();

      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();
        
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
        
      } else if (command.equals("/board/detail")) {
        boardHandler.detailBoard();
        
      } else if (command.equals("/board/update")) {
        boardHandler.updateBoard();
        
      } else if (command.equals("/board/delete")) {
        boardHandler.deleteBoard();

      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();
        
      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();
        
      } else if (command.equals("/board2/detail")) {
        boardHandler2.detailBoard();
        
      } else if (command.equals("/board2/update")) {
        boardHandler2.updateBoard();
        
      } else if (command.equals("/board2/delete")) {
        boardHandler2.deleteBoard();

      } else {
        System.out.println("해당 명령은 지원하지 않습니다.");
      }
        
      System.out.println();
    }
  }

//메소드 이름은 동사형 또는 동사로 시작한다.
  public static String prompt() {
    System.out.print("명령> ");
    return keyScan.nextLine();
  }

}
