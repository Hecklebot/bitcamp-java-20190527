package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  private static final int DEFAULT_CAPACITY = 100;
  private Member[] list = new Member[100];
  private int size = 0;
  
  public MemberList() {
    this(DEFAULT_CAPACITY);
  }
  
  public MemberList(int initialCapacity) {
    if(initialCapacity < 50 || initialCapacity > 10000) {
      this.list = new Member[DEFAULT_CAPACITY];
    } else {
      this.list = new Member[initialCapacity];
    }
  }
  
  public void add(Member member) {
    if(this.size == list.length) {
      throw new RuntimeException("배열에 자리가 없습니다.");
    } else {
      this.list[this.size++] = member;
    }
  }
  
  public Member[] toArray() {
    Member[] arr = new Member[size];
    for(int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

}
