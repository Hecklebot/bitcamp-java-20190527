package ch14.b;

public class X2 extends X1 {
  @Override
  void m1() {
    System.out.println("X2.m1()");
  }
  
  void test() {
    this.m1(); //this에 저장된 인스턴스의 주로의 클래스에서 m1()을 찾고 없다면 슈퍼 클래스로 올라간다.(찾을 때까지)
    super.m1(); //이 메서드가 호출된 주소와 상관없이 슈퍼 클래스부터 m1()을 찾아 올라간다.
  }
}