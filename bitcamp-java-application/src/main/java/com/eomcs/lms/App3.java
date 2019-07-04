package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    InputStream keyboard = System.in;
    Scanner scan = new Scanner(keyboard);
    
    System.out.print("번호? ");
    String number = scan.nextLine();
    
    System.out.print("내용? ");
    String input = scan.nextLine();
        
    System.out.println();
    
    System.out.println("번호: " + number);
    System.out.println("내용: " + input);
    System.out.println("작성일: 2019-01-01");
    System.out.println("조회수: 0");

    
  }
}
