// 일반 메서드의 한계 - 서브 클래스에게 구현을 강요할 수 없다.
package ch17.d;

public class Test02 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    
    // 새로 추가한 MergeSort를 사용하여 데이터를 정렬해 보자!
    // => Sorter를 상속 받은 것까지는 잘했다.
    // => 그런데 사용하는 개발자 쪽에서는 Sorter를 상속 받았으니, 당연히 정렬을 수행하기 위해 sort()메서드를 호출할 것이다.
    //    그러나 MerseSort 클래스는 sort()메서드를 오버라이딩 하지 않았다.
    // => 개발자가 sort()를 오버라이딩 하는 것을 놓친 것이다.
    // => 이런 실수나 의도를 방지하고자 등장하 문법이 "추상 메서드"이다.
    //    서브 클래스에게 구현을 강제할 필요가 있을 때 추상 메서드로 선언하라!
    //    이것은 e 패키지에서...
    display(new MergeSort(), values3);
    
  }

  static void display(Sorter sorter, int[] values) {
    
    //당연히 파라미터로 넘어오는 클래스는 Sorter의 서브클래스일 것이고, Sorter는 추상클래스이기 때문에 인스턴스를 생성할 수 없다.
    //따라서, Sorter의 인스턴스가 넘어올 수 없는 것이다.
    //따라서, 정렬을 수행하려면 Sorter에 정의된 sort()를 호출할 것이다.
    //여기서 문제가 발생하는데, BubbleSort나 QuickSort 클래스는 sort()를 오버라이딩 하였다. 그러나 MergeSort는 sort()를 오버라이딩 하지 않아서,
    //따라서 파라미터로 넘어오는 객체가 MergeSort라면 다음 메서드를 호출하는 것은 아무런 의미가 없을 것이다.
    
    //해결방안
    // -> Sorter의 서브 클래스들이 sort()를 반드시 구현하도록 강제하라
    // -> 어떻게? 추상 메서드로 선언해서

    sorter.sort(values);
    
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






