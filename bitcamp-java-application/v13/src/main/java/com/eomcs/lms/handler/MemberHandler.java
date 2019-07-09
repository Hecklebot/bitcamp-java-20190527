package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberHandler {
  private  Member[] members = new Member[100];
  private  int membersSize = 0;

  public static Scanner keyScan;
  
  public  void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s , %s\t, %s\t, 2019-01-01\n", member.name, member.email, member.tel);
    }
  }

  public  void addMember() {
    Member member = new Member();

    member.no = getIntValue("번호? ");
    member.name = getStringValue("이름? ");
    member.email = getStringValue("이메일? ");
    member.password = getStringValue("비밀번호? ");
    member.photo = getStringValue("사진? ");
    member.tel = getStringValue("전화번호? ");
    member.registeredDate = getDateValue("가입일? ");

    members[membersSize++] = member;
    System.out.println("회원 저장완료");
  }

  
  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-01-01과 같이 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

}
