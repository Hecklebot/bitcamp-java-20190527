package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  private static Board[] boards = new Board[100];
  private static int boardsSize = 0;

  public static Scanner keyScan;

  
  public static void addBoard() {
    Board board = new Board();

    board.no = getIntValue("번호? ");
    board.contents = getStringValue("내용? ");
    board.createdDate = getDateValue("작성일? ");
    boards[boardsSize++] = board;
    System.out.println("게시물 저장완료");
  }

  
  public static void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = new Board();
      board = boards[i];
      System.out.printf("%s 번째 게시글입니다.\t %s, %s, \n", board.no, board.contents,
          board.createdDate);
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

}
