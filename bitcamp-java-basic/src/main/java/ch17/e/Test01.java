// 추상 메서드의 효용성 - 서브 클래스에서 구현하도록 강제하는 효과가 있다.
package ch17.e;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    //Sorter 클래스의 sort()메서는 추상 메서드이기 때문에, Sorter 클래스를 상속받은 서브 클래스가 무엇이든 간에
    //sort()메서드가 반드시 구현되어 있을 것이다. 따라서, display()에서는 파라미터로 넘어오는 Sorter가
    //BubbleSort인지, QuickSort인지, MergeSort인지 구분할 필요 없이, 그냥 서브 클래스가 구현한 sort()를 호출하면 된다. 
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    
    // 이제 MergeSort는 Sorter의 추상 메서드인 sort()를 구현했다.
    // 정상적으로 동작할 것이다.
    
    display(new MergeSort(), values3);
    
  }

  static void display(Sorter sorter, int[] values) {
    
    //Sorter 클래스의 sort()는 추상 메서드인데 어떻게 호출하는가?
    //JVM이 sort()를 호출할 때 레러펀스에 있느 객체가 어떤 클래스의 객체인지 판단한다.
    //그 후 해당 크래스의 메서드를 호출한다.
    sorter.sort(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






