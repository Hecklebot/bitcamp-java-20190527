//상속 문법을 이용하여 스택 만들기

package com.eomcs.util;

public class Stack<E> extends LinkedList<E> implements Cloneable {
  
  @Override
  public Stack<E> clone() throws CloneNotSupportedException { 
    //shallow copy가 되지 않도록 새 Stack를 만들어 값을 넣는다. 노드는 클론되지 않는다.
    // -> 새 스택을 만들고, 
    Stack<E> temp = new Stack<>();
    for(int i = 0; i < size(); i++) {
      // -> 현재 스택에서 값을 꺼내 새 스택에 넣는다.
      // -> 새 스택을 값을 넣을 때 마다 새 노드를 생성하기 때문에, 현재 스택의 노드와는 영향을 주지 않는다.
      temp.push(get(i));
    }
    return temp;
  }
  
  public void push(E value) {
    add(value);
  }
  
  public E pop() {
    return remove(size()-1);
  }

  public boolean empty() {
    return size() == 0;
  }
}
