package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App2 {
  
  static Scanner scan;
  
  public static void main(String[] args) {
    
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
    
    int[]number = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    String[] password = new String[100];
    String[] picture = new String[100];
    String[] phoneNumber = new String[100];
    
    int i=0;
    for(; i<number.length;i++) {
      number[i] = getIntValue("번호는? ");
      name[i] = getStringValue("이름은? ");
      email[i] = getStringValue("이메일은? ");
      password[i] = getStringValue("암호는? ");
      picture[i] = getStringValue("사진은? ");
      phoneNumber[i] = getStringValue("전화번호는? ");
      
      System.out.print("계속 입력하시겠습니까? (Y/n)");
      String response = scan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
    
    int i2=0;
    for(;i2<=i;i2++) {
      System.out.printf("%s , %s\t, %s\t, 2019-01-01\n", name[i2], email[i2], phoneNumber[i2]);
    }
    
   
    
    System.out.println();
    

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
