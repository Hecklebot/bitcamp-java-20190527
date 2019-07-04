package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    
    InputStream keyboard = System.in;
    Scanner scan = new Scanner(keyboard);
    
    System.out.print("번호? ");
    String number = scan.nextLine();
    System.out.print("이름? ");
    String name = scan.nextLine();
    System.out.print("이메일? ");
    String eMail = scan.nextLine();
    System.out.print("암호? ");
    String password = scan.nextLine();
    System.out.print("사진? ");
    String picture = scan.nextLine();
    System.out.print("전화? ");
    String phoneNumber = scan.nextLine();
    
    System.out.println();
    
    System.out.println("번호: " + number);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + eMail);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNumber);
    System.out.println("가입일: 2019-01-01");
    

  }

}
