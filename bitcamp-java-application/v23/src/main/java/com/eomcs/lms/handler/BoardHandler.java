package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;
import com.eomcs.util.List;



public class BoardHandler {

  //BoardHandler가 사용하는 Input객체를 반드시 설정하도록 강제해보자.
  //-> Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를 의존객체(dependency)라 부른다.
  //-> 의존객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  //   의존객체를 넘겨받는 생성자를 정의하는 것이다.
  private Input input;
  private List<Board> boardList;
  
  public BoardHandler(Input input, List<Board> list) {
    this.input = input;
    this.boardList = list;
  }
  
  public void addBoard() {
    Board board = new Board();
    
    board.setNo(input.getIntValue("번호? "));
    board.setContents(input.getStringValue("내용? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    
    boardList.add(board);
    System.out.println("저장완료");
  }

  public void listBoard() {
//  Board[] boards = boardList.toArray(new Board[] {}); //일단 0크기 배열 만들어서 toArray 안에서 조건문으로 처리한다. -> 배열이 0이므로 새 배열에 복사하고 새 배열 주소를 리턴
    Board[] boards = new Board[boardList.size()]; //addBoard할 때 마다 size는 1씩 증가해서 배열의 크기는 addBoard한 횟수이다.
    boardList.toArray(boards); //toArray 메서드는 새로 선언한 boards에 addBoard해서 BoardList 클래스에 추가된 데이터를 넣는다.
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(), board.getCreatedDate(),
          board.getViewCount());
    }
  }

  public void detailBoard() {
    int no = input.getIntValue("번호? ");
    Board board =null;
    
    for(int i=0;i<boardList.size();i++) {
      Board temp = boardList.get(i);
      if(temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    
    if(board == null) {
      System.out.println("게시글이 없습니다.");
      return;
    }
    
    System.out.println(board.getNo());
    System.out.println(board.getContents());
    System.out.println(board.getCreatedDate());
    
  }

  public void updateBoard() {
    int no = input.getIntValue("번호? ");
    Board board =null;
    
    for(int i=0;i<boardList.size();i++) {
      Board temp = boardList.get(i);
      if(temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    
    if(board == null) {
      System.out.println("게시글이 없습니다.");
      return;
    }
    
    String str = input.getStringValue("변경할 내용? ");
    if(str.length() > 0) {
      board.setContents(str);
      System.out.println("변경이 완료되었습니다.");
    }
    
  }

  public void deleteBoard() {
    int no = input.getIntValue("번호? ");
    for(int i=0;i<boardList.size();i++) {
      Board board = boardList.get(i);
      if(board.getNo() == no) {
        boardList.remove(i);
        System.out.println("게시글이 삭제되었습니다.");
        return;
      }
    }
    
    System.out.println("게시글이 없습니다.");
    
  }
}
