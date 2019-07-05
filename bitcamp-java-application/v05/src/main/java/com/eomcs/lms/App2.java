package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App2 {
  
  static Scanner scan;
  
  public static void main(String[] args) {
    
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
    
    int number = getIntValue("번호는? ");
    String name = getStringValue("이름은? ");
    String eMail = getStringValue("이메일은? ");
    String password = getStringValue("암호는? ");
    String picture = getStringValue("사진은? ");
    String phoneNumber = getStringValue("전화번호는? ");
    
    System.out.println();
    
    System.out.println("번호: " + number);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + eMail);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNumber);
    System.out.println("가입일: 2019-01-01");
    

  }
  
  private static int getIntValue(String message) {
    while(true) {
      try {
        System.out.print(message);
        return Integer.parseInt(scan.nextLine());
      } catch(NumberFormatException e){
        System.out.println("숫자를 입력하시오.");
      }
    }
  }
  
  private static String getStringValue(String message) {
        System.out.print(message);
        return scan.nextLine();
  }
  
  
  
  
}
