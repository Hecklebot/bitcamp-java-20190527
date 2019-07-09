package com.eomcs.lms;

public class StaticTest {
  public static void main(String[] args) {
    //static field 사용하기
    //-> 클래스 이름으로 사용하면 된다.
    System.out.println(Car.count);
    
    //instance field 사용하기.
    Car c1;
    c1 = new Car();
    c1.no = 1;
    c1.model = "tico";
    
    Car.count++; //static field는 보통 클래스 이름으로 사용한다.
                 //class 대신 reference로 사용할 수 있다. instance field라고 착각할 수 있기 때문에 추천하지 않는다.
    Car c2 = new Car();
    c2.no = 2;
    c2.model = "sonata";
    c2.count++; 
    
    System.out.printf("%d, %s\n", c1.no, c1.model);
    System.out.printf("%d, %s\n", c2.no, c2.model);
    System.out.println(Car.count);
  }
}
