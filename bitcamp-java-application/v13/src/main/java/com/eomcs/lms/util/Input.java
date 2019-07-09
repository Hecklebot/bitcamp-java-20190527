package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input { //instance field를 사용하지 않는 클래스는 그냥 static method로 
                     //input2, input3, input4... 필요없음
  
  public static Scanner keyScan;
  
  public static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  public static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-01-01과 같이 입력하세요.");
      }
    }
  }

  public static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }
  
  public static String prompt() {
    System.out.print("명령> ");
    String command = keyScan.nextLine();
    return command;
  }
}
