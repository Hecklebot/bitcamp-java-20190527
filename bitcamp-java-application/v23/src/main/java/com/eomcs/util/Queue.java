//상속 문법을 이용하여 큐 만들기

package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {
  
  @Override
  public Queue<E> clone() throws CloneNotSupportedException { 
    //shallow copy가 되지 않도록 새 Stack를 만들어 값을 넣는다. 노드는 클론되지 않는다.
    // -> 새 큐를 만들고, 
    Queue<E> temp = new Queue<>();
    for(int i = 0; i < size(); i++) {
      // -> 현재 큐에서 값을 꺼내 새 큐에 넣는다.
      // -> 새 큐을 값을 넣을 때 마다 새 노드를 생성하기 때문에, 현재 스택의 노드와는 영향을 주지 않는다.
      temp.offer(get(i));
    }
    return temp;
  }
  
  public void offer(E value) {
    add(value);
  }
  
  public E poll() {
    return remove(0);
  }

  public boolean empty() {
    return size() == 0;
  }
}
