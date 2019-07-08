package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  
  public static Scanner keyScan;
  private static Board[] boards = new Board[100];
  private static int boardsSize =0;

  public static void addBoard() {
    Board board = new Board();
    
    board.no = Input.getIntValue("번호? ");
    board.contents = Input.getStringValue("내용? ");
    board.createdDate = Input.getDateValue("작성일? ");
    boards[boardsSize++] = board;
    System.out.println("저장완료");
  }

  public static void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }
}
