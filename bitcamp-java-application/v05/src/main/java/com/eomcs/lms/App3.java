package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App3 {
  static Scanner scan;
  public static void main(String[] args) {
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
    
    int number = getIntValue("번호? ");
    String input = getStringValue("내용?");
        
    System.out.println();
    
    System.out.println("번호: " + number);
    System.out.println("내용: " + input);
    System.out.println("작성일: 2019-01-01");
    System.out.println("조회수: 0");
    
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
  
  
  
}
