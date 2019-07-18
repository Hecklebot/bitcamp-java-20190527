package algorithm.data_structure.linkedlist2.step1;

public class LinkedListTest {

  public static void main(String[] args) {
    LinkedList l = new LinkedList();
    l.add("aaa");
    l.add("bbb");
    l.add("ccc");
    l.add("ddd");
    l.add("eee");
    l.add("fff");
    l.add("ggg");
    
    l.set(2, "xxx"); //"ccc" -> "xxx"
    for(int i = 0; i<l.size;i++) {
      System.out.print(l.get(i) + "\t");
    }
    System.out.println();
    
    l.remove(3); // "ddd" 삭제
    l.remove(0); // "aaa" 삭제
    l.remove(l.size()-1); // "ggg" 삭제
    l.remove(0); // "bbb" 삭제
    l.remove(0); // "xxx" 삭제
    l.remove(0); // "eee" 삭제
    l.remove(0); // "fff" 삭제
    
    l.add("okok");
    l.add("nono");
    l.add("hul");
    
    
    for(Object obj : l.toArray()) {
      System.out.println(obj);
    }
  }

}
