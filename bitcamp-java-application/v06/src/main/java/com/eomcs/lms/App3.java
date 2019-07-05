package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App3 {
  static Scanner scan;
  public static void main(String[] args) {
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
    
    int[] number = new int[100];
    String[] input = new String[100]; 
    
    int i=0;
    for(;i<number.length;i++) {
      number[i] = getIntValue("번호? ");
      input[i] = getStringValue("내용? ");
      
      System.out.println("계속 입력하시겠습니까? (Y/n)");
      String response = scan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
    
    System.out.println();
    
    int i2=0;
    for(;i2<=i;i2++) {
      System.out.printf("%s 번째 게시글입니다.\t %s, 2019-01-01, \n", number[i2], input[i2]);
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
  
  
  
}
