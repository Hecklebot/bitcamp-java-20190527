//LinkedList가 디폴트 생성자를 쓸 때

package com.eomcs.util;

public class LinkedList_Test2 {
  Node head;
  Node tail;
  int size = 0;
  public LinkedList_Test2() {
  }
  
  public boolean add(Object obj) {
    Node temp = new Node();
    if(head == null) {
      head = temp;
    }
    if(tail != null) {
      tail.next = temp;
    }
    tail = temp;
    tail.value = obj;
    
    size++;
    return true;
  }
  public static void main(String[] args) {
    LinkedList_Test2 lt = new LinkedList_Test2();
    lt.add("abd");
    lt.add("bbb");
    
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");
    lt.add("ccc");

  }
  
  static class Node {
    Object value; //저장할 값
    Node next;    //다음 리스트의 주소
    
    public Node(Object value) {
      this.value = value;
    }
    
    public Node() {

    }
  }
  
}
