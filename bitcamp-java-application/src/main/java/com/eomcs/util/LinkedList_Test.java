//LinkedList의 생성자로 새 노드를 만들었을 때

package com.eomcs.util;

public class LinkedList_Test {
  Node head;
  Node tail;
  int size = 0;
  public LinkedList_Test() {
    head = new Node();
    tail = head;
  }
  
  public boolean add(Object obj) {
    tail.value = obj;
    head.next = new Node();
    tail = head.next;
    
    size++;
    return true;
  }
  public static void main(String[] args) {
    LinkedList_Test lt = new LinkedList_Test();
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
