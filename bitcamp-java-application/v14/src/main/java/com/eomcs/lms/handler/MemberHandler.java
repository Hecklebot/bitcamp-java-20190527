package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  
  private Member[] members = new Member[100];
  private int membersSize = 0;
  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {
    Member member = new Member();
    
    member.no = input.getIntValue("번호? ");
    member.name = input.getStringValue("이름? ");
    member.email = input.getStringValue("이메일? ");
    member.password = input.getStringValue("비밀번호? ");
    member.tel = input.getStringValue("전화번호? ");
    member.photo = input.getStringValue("사진? ");
    member.registeredDate = input.getDateValue("가입일? ");  

    members[membersSize++] = member;
    System.out.println("저장완료");
  }

  public void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.name, member.email, member.tel,  
         member.registeredDate);
    }
  }

}
