package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class MemberHandler {
  
  private Input input;
  private ArrayList memberList = new ArrayList();
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {
    Member member = new Member();
    
    member.setNo(input.getIntValue("번호? "));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPassword(input.getStringValue("비밀번호? "));
    member.setTel(input.getStringValue("전화번호? "));
    member.setPhoto(input.getStringValue("사진? "));
    member.setRegisteredDate(input.getDateValue("가입일? "));  
    memberList.add(member);
    System.out.println("저장완료");
  }

  public void listMember() {
    Object[] members = memberList.toArray();
    for (Object obj : members) {
      Member member = (Member) obj;
      System.out.printf("%s, %s, %s, %s\n", member.getName(), member.getEmail(), member.getTel(),
         member.getRegisteredDate());
    }
  }

}
