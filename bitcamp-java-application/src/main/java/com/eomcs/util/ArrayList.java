package com.eomcs.util;

public class ArrayList {
  private static final int DEFAULT_CAPACITY = 100;
  
  private Object[] list;
  private int size = 0;
  
  public ArrayList() {                //몇 개 만들건지 안받으면 자동으로 100개 까지
    this(DEFAULT_CAPACITY); //this: 레퍼런스가 아니고 그냥 자기자신의 생성자를 의미
  }
  
  public ArrayList(int initialCapacity) { //받으면 받은 숫자만큼
    if(initialCapacity < 50 || initialCapacity > 10000) {
      list = new Object[DEFAULT_CAPACITY];
    } else {
      list = new Object[initialCapacity];
    }
  }
  
  public void add(Object obj) {
//  this(100); //일반 메소드는 생성자 호출 불가
//  BoardList(100);
    if(this.size == list.length) {
      throw new RuntimeException("배열에 자리가 없습니다.");
    } else {
      this.list[size++] = obj;
    }
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size];
    for(int i=0; i<size;i++) {
      arr[i] = list[i];
    }
    return arr;
  }
  
}
