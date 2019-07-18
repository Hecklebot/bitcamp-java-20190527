package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;

public class MemberHandler {
  
  private Input input;
  private LinkedList<Member> memberList = new LinkedList<>();
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
    Member[] members = new Member[memberList.size()];
    memberList.toArray(members);
    for (Member member : members) {
      System.out.printf("%s, %s, %s, %s\n", member.getName(), member.getEmail(), member.getTel(),
         member.getRegisteredDate());
    }
  }

  public void detailMember() {
    int no = input.getIntValue("번호? ");
    Member member = null;
    
    for(int i=0;i<memberList.size();i++) {
      Member temp = memberList.get(i);
      if(temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    
    if(member == null) {
      System.out.println("입력한 데이터가 없습니다.");
      return;
    }
    
    System.out.println(member.getNo());
    System.out.println(member.getName());
    System.out.println(member.getEmail());
    System.out.println(member.getPassword());
    System.out.println(member.getPhoto());
    System.out.println(member.getTel());
    System.out.println(member.getRegisteredDate());
    
    
  }

  public void updateMember() {
    int no = input.getIntValue("번호? ");
    Member member = null;
    
    for(int i=0;i<memberList.size();i++) {
      Member temp = memberList.get(i);
      if(temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    
    if(member == null) {
      System.out.println("입력한 데이터가 없습니다.");
      return;
    }
    
    String str = input.getStringValue("이름? ");
    if(str.length() > 0) {
      member.setName(str);
    }
    
    str = input.getStringValue("이메일? ");
    if(str.length() > 0) {
      member.setEmail(str);
    }
    
    str = input.getStringValue("암호? ");
    if(str.length() > 0) {
      member.setPassword(str);
    }
    
    str = input.getStringValue("사진? ");
    if(str.length() > 0) {
      member.setPhoto(str);
    }
    
    str = input.getStringValue("전화번호? ");
    if(str.length() > 0) {
      member.setTel(str);
    }
    
    member.setRegisteredDate(input.getDateValue("가입일? "));
    System.out.println("회원정보 수정 완료");
  }

  public void deleteMember() {
    int no = input.getIntValue("번호? ");
    
    for(int i=0;i<memberList.size();i++) {
      Member member = memberList.get(i);
      if(member.getNo() == no) {
        memberList.remove(i);
        System.out.println("회원정보가 삭제되었습니다.");
        return;
      }
    }
      System.out.println("입력한 데이터가 없습니다.");
    }

}
