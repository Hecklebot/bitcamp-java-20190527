package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> { // 타입 정보를 받는 변수: 타입 파라미터
  private static final int DEFAULT_CAPACITY = 100;

  private Object[] list;
  private int size = 0;

  public ArrayList() { // 몇 개 만들건지 안받으면 자동으로 100개 까지
    this(DEFAULT_CAPACITY); // this: 레퍼런스가 아니고 그냥 자기자신의 생성자를 의미
  }

  public ArrayList(int initialCapacity) { // 받으면 받은 숫자만큼
    if (initialCapacity < 50 || initialCapacity > 10000) {
      list = new Object[DEFAULT_CAPACITY];
    } else {
      list = new Object[initialCapacity];
    }
  }

  public void add(E obj) {
    // this(100); //일반 메소드는 생성자 호출 불가
    // BoardList(100);
    if (this.size == list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1); // >> : 비트 연산자
      list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[size++] = obj;
  }

  public Object[] toArray() { //쓰려면 형변환 해서 써야함
    return Arrays.copyOf(this.list, this.size); // -> new Object[this.size];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) { //받은 배열 크기가 더 작으면? 새 배열 만들어서 리턴
      // 파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 갯수보다 작다면, 이 메서드에서 새 배열을 만든다.
    //Arrays.copyOf(original, newLength, newType)
      return (E[]) Arrays.copyOf(list, size, a.getClass()); // new E[this.size]; -> 안된다. // 세 번째 파라미터로 지정한 타입의 배열이 생성된다.
    }
    
    System.arraycopy(list, 0, a, 0, size); //받은 배열 크기가 딱 맞다면? list를 복사해와서 리턴
    
    if (a.length > size) { //받은 배열 크기가 더 크다면? 받은 배열 남는 곳 잘라내고 리턴
      a[size] = null;
    }
    return a;
  }

  public int size() {
    return this.size;
  }
  
//  public static void main(String[] args) {
//    ArrayList<Board> boards = new ArrayList<>();
//    Board b = new Board();
//    b.setContents("aaaa");
//    boards.add(b);
//
//    b = new Board();
//    b.setContents("bbbb");
//    boards.add(b);
//
//    b = new Board();
//    b.setContents("cccc");
//    boards.add(b);
//
//    Board[] boards2 = new Board[2];
//    boards.toArray(boards2);
//
//    for (Board board : boards2) {
//      System.out.println(board.getContents());
//    }
//
//  }
}
