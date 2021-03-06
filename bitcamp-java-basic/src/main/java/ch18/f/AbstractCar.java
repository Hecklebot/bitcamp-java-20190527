// 추상 클래스를 이용하여 인터페이스를 구현하고, 인터페이스의 일부 메서드를 미리 정의해 둠으로써 
// 서브 클래스가 인터페이스의 모든 메서드를 재정의하는 것을 줄인다. 서브 클래스가 1억개면?
//  -> 편하다
// 인터페이스의 메서드가 많을 때 특히 많이 사용하는 방식. 혹은 서브 클래스가 아주 많거나 
// 즉, 인터페이스를 직접 구현하기 보단, 추상 클래스를 사용하여 상속받아 간접적으로 구현하는 기법을 사용한다.
package ch18.f;

public abstract class AbstractCar implements CarSpec {
  
  // 추상 클래스의 목적은 서브 클래스에게 공통 필드와 공통 메서드를 상속해주는 것이다. 
  String model;
  int cc;

  // -> 그 중에서 인터페이스에 선언된 메서드의 일부를 미리 구현해주면, 서브클래스를 만들기가 매우 편리하다.
  public void on() {
    System.out.println("시동을 켠다.");
  }
  
  public void off() {
    System.out.println("시동을 끈다.");
  }
  
  // 상속해주는 메서드 중에서 일부는 서브 클래스가 구현하도록 강제할 수 있다.
  // => 즉, 인터페이스의 메서드 중, 서브 클래스에서 구현해야만 의미가 있는 메서드의 경우, 추상 클래스에서 구현하지 않고 추상 메서드로 둔다.
  // -> 그래서 서브 클래스가 구현하도록 강제할 수 있다.
  public abstract void run();
}







