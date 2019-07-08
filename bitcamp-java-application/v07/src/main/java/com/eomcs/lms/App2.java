package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App2 {
  
  static Scanner scan;
  
  public static void main(String[] args) {
    
    InputStream keyboard = System.in;
    scan = new Scanner(keyboard);
    
    Member[] members = new Member[100];
    
    for(int i=0; i<members.length;i++) {
      members[i] = new Member();
    }
    
    int i=0;
    for(;i<members.length;i++) {
      members[i].name = getStringValue("이름: ");
      members[i].PhoneNumber = getStringValue("전화번호: ");
      members[i].age = getIntValue("나이: ");
      members[i].gender = getStringValue("성별: ");
      members[i].address = getStringValue("주소: ");
      
      System.out.print("계속 입력하시겠습니까? (Y/n)"); 
      String response = scan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
    
    int i2=0;
    for(;i2<=i;i2++) {
      System.out.printf("%s, %s, %s, %s, %s\n", members[i2].name, members[i2].PhoneNumber, 
          members[i2].age, members[i2].gender, members[i2].address);
    }
    
    
    
    
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
