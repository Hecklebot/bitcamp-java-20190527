package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;

public class BoardList {
  private static final int DEFAULT_CAPACITY = 100;
  private Board[] list;
  private int size = 0;
  
  public BoardList() {                //몇 개 만들건지 안받으면 자동으로 100개 까지
    this(DEFAULT_CAPACITY); //this: 레퍼런스가 아니고 그냥 자기자신의 생성자를 의미
  }
  
  public BoardList(int initialCapacity) { //받으면 받은 숫자만큼
    if(initialCapacity < 50 || initialCapacity > 10000) {
      list = new Board[DEFAULT_CAPACITY];
    } else {
      list = new Board[initialCapacity];
    }
  }
  
  public void add(Board board) {
//  this(100); //일반 메소드는 생성자 호출 불가
//  BoardList(100);
    if(this.size == list.length) {
      throw new RuntimeException("배열에 자리가 없습니다.");
    } else {
      this.list[size++] = board;
    }
  }
  
  public Board[] toArray() {
    Board[] arr = new Board[size];
    for(int i=0; i<size;i++) {
      arr[i] = list[i];
    }
    return arr;
  }
  
}
