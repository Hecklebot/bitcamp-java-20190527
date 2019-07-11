package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  
  private Board[] boards = new Board[100];
  private int boardsSize =0;
  
  //BoardHandler가 사용하는 Input객체를 반드시 설정하도록 강제해보자.
  //-> Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를 의존객체(dependency)라 부른다.
  //-> 의존객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  //   의존객체를 넘겨받는 생성자를 정의하는 것이다.
  private Input input;
  
  public BoardHandler(Input input) {
    this.input = input;
  }
  
  
  
  public void addBoard() {
    Board board = new Board();
    
    board.no = input.getIntValue("번호? ");
    board.contents = input.getStringValue("내용? ");
    board.createdDate = input.getDateValue("작성일? ");
    boards[boardsSize++] = board;
    System.out.println("저장완료");
  }

  public void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }
}
