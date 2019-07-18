package algorithm.data_structure.linkedlist2.step1;

public class LinkedList {
  Node head;
  Node tail;
  int size = 0;

  public LinkedList() {}

  public boolean add(Object value) {
    Node temp = new Node(value);
    if (head == null) {
      head = temp;
    }

    if (tail != null) {
      tail.next = temp;
    }
    tail = temp;
    size++;
    return true;

    // 결과 : head의 value는 null next는 다음 Node, tail에 head.next를 대입하고, tail.vaule에 입력받은 value,
    // tail.next은 null
  }

  public Object get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("인덱스가 유효하지 않습니다. length = %s", this.size));
    }

    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  public Object set(int index, Object value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("인덱스가 유효하지 않습니다. length = %s", this.size));
    }

    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    Object oldValue = node.value; // 노드에 저장된 기존 값 백업
    node.value = value;
    return oldValue; // 기존 변경값 리턴
  }


  public int size() {
    return size;
  }

  // 특정 위치의 값을 삭제하는 remove 메서드
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format("인덱스가 유효하지 않습니다. length = %s", this.size));
    }
    Node deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {
      Node node = head;
      for (int i = 0; i < index - 1; i++) {
        // 삭제하려는 노드의 이전 노드까지 간다.
        node = node.next;
      }

      deletedNode = node.next; // 삭제될 노드를 임시 보관한다.
      node.next = deletedNode.next; // 삭제될 노드의 다음 노드를 가리킨다.

      if (deletedNode == tail) { // 삭제할 노드가 마지막 노드라면
        tail = node; // tail 노드를 변경한다.
      }
    }

    Object oldValue = deletedNode.value; // 삭제될 노드의 값을 보관한다.
    deletedNode.value = null; // 삭제될 노드가 다른 객체를 참조하지 않도록 초기화한다.
    deletedNode.next = null; // 이런 식으로 개발자가 메모리 관리에 기여할 수 있다.

    size--;
    return oldValue; // 기존 변경값 리턴
  }

  public void clear() {
    if(size == 0) {
      return;
    }
    
    //노드를 따라가면서 삭제하기
    while(head != null) {
      Node deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;
    }
    
    head = tail = null;
    size = 0;
  }

  public Object[] toArray() {
    //LinkedList에 있는 데이터를 저장할 배열을 준비한다.
    Object[] obj = new Object[size];
    
    //LinkedList의 head부터 tail까지 반복하면서 배열에 value를 리턴한다.
    Node temp = head;
    for(int i=0; i<size ;i++) {
      obj[i] = temp.value;
      temp = temp.next;
    }
    
    return obj;
    
//    Node temp = head;
//    Object[] arr = new Object[size];
//    int i=0;
//    while(temp != null) {
//      arr[i++] = temp.value;
//      temp = temp.next;
//    }
//
//    return arr;
  }
}
