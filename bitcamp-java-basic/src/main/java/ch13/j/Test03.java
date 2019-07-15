// 추상 클래스: 리턴받기
package ch13.j;

public class Test03 {

  public static void main(String[] args) {
    DumpTruck car = m1();    //-> 된다.
    //m2(new Car());        //-> 안된다. (과자사와 -> 과자no)
    DumpTruck car2 = (DumpTruck)m2();  //-> 된다.  (과자사와 -> 새우깡 ok, 꿀꽈배기 ok, 짱구ok)
    Car car3 = m2();
    
    
  }
  static DumpTruck m1() {
    //이 메서드의 리턴값은 DumpTruck의 인스턴스 이거나, 하위 클래스의 인스턴스이다.
    return new DumpTruck();
  }
  
  static Car m2() {
    //이 메서드의 리턴값은 Car가 아니다.(Car는 추상클래스이기 때문에 인스턴스를 생성할 수 없다.)
    //Car의 하위 클래스의 인스턴스를 리턴한다는 뜻이다.
    //대신 리턴 타입과 타입 캐스팅을 해야한다.
    return new DumpTruck();
  }
}
