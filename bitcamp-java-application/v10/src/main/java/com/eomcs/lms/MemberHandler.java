package com.eomcs.lms;

import java.util.Scanner;

public class MemberHandler {
  static Scanner keyScan;
  static Member[] members = new Member[100];
  static int membersSize = 0;

  
  static void addMember() {
    Member member = new Member();
    
    member.no = Input.getIntValue("번호? ");
    member.name = Input.getStringValue("이름? ");
    member.email = Input.getStringValue("이메일? ");
    member.password = Input.getStringValue("비밀번호? ");
    member.tel = Input.getStringValue("전화번호? ");
    member.photo = Input.getStringValue("사진? ");
    member.registeredDate = Input.getDateValue("가입일? ");  

    members[membersSize++] = member;
    System.out.println("저장완료");
  }

  static void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.name, member.email, member.tel,  
         member.registeredDate);
    }
  }

}
