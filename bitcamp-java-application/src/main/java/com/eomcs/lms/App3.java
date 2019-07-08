package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App3 {
  static Scanner scan;
  public static void main(String[] args) {
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
   
    Board[] boards = new Board[100];
    
    int i=0;
    for(;i<boards.length;i++) {
      Board board = new Board();
      
      board.writer = getStringValue("작성자: ");
      board.contents= getStringValue("내용: ");
//      board.writeDate = getDateValue("작성일: ");
      board.viewCount = getIntValue("조회수: ");
      
      boards[i] = board;
      
      System.out.println("계속 입력?");
      String response = scan.nextLine();
      if(response.equals("n")) {
        break;
      }
      
    }
    
    int i2=0;
    for(;i2<=i;i2++) {
      Board board = boards[i2];
      System.out.printf("%s,\t %s,\t %s,\t %s\n", board.writer, board.contents, 
          board.writeDate, board.viewCount);
    }
    
    
    
    
    
  }
  
  
  
  
  private static int getIntValue(String message) {
    while(true) {
      try {
        System.out.print(message);
        return Integer.parseInt(scan.nextLine());
      } catch(NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }
  
  private static String getStringValue(String message) {
    System.out.print(message);
    return scan.nextLine();
  }
  
  private static java.sql.Date getDateValue(String message){
    while(true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(scan.nextLine());
      } catch(IllegalArgumentException e) {
        System.out.println("2019-01-01과 같이 입력하세요.");
      }
    }
  }

  
  
}
