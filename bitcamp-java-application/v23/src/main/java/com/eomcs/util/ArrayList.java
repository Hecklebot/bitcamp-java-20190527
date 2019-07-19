package com.eomcs.util;

import java.util.Arrays;

//ArrayList 클래스를 List 규칙에 따라 작성한다.
// -> 클래스를 선언할 때, 어떤 규칙을 따를 것인지 지정해야 한다.
// -> ClassName implements 규칙1, 규칙2, 규칙3,...
public class ArrayList<E> implements List<E>{ // 타입 정보를 받는 변수: 타입 파라미터
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
  
  //인터페이스에 정의된 메서드를 구현할 때는 오버라이딩과 같아, 접근 권한을 줄일 수 없다.
  // -> public을 더 제한할 수 없다.
  // -> @Override 불일 수 있다.
  @Override
  public boolean add(E obj) {
    // this(100); //일반 메소드는 생성자 호출 불가
    // BoardList(100);
    if (this.size == list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1); // >> : 비트 연산자
      list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[size++] = obj;
    return true;
  }
  @Override
  public Object[] toArray() { //쓰려면 형변환 해서 써야함
    return Arrays.copyOf(this.list, this.size); // -> new Object[this.size];
  }
  
  @Override
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
  @Override
  public int size() {
    return this.size;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("index = %s", index));
    }
    return (E) list[index];
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public E set(int index, E obj) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("index = %s", index));
    }
    E old = (E) list[index];
    list[index] = obj;
    
    return old;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("index = %s", index));
    }
    
    E old = (E) list[index];
    // 방법 1 : 직접 반복문을 이용하여 삭제 처리하기
    /*
    for(int i = index; i<size; i++) {
      list[i] = list[i+1];
    }
    list[--size] = null; //배열의 크기가 하나 줄었으니 줄어들은 끝 자리를 null처리한다. -> 메모리 절약
     */
    
    // 방법 2 : 배열 복사를 이용해 삭제 처리하기
    System.arraycopy(list, index+1, list, index, size - (index + 1));
    list[--size] = null; //삭제한 후 기존 맨 끝방 값은 null로 설정한다.
                           //-> 레퍼런스가 남아있지 않게 하여 가비지 컬렉터가 정상적으로 활동하도록 한다.
    
    
    return old;
  }
  
  @Override
    public void clear() {
      for(int i = 0; i<size; i++) {
        list[i] = null;
      }
      size = 0;
    }
}
