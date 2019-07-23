// 중첩 클래스 종류 
package ch19.a;

public class Test02 {

  // 중첩 클래스
  // => 특정 클래스 안에서만 사용되는 클래스가 있다면 중첩 클래스로 선언하라.
  // => 즉 노출 범위를 좁히는 것이 유지보수에 좋다.
  //
  // 1) static nested class 
  // => 바깥 클래스의 특정 인스턴스에 종속되지 않는 클래스.
  static class A {}
  // -> 다른 스태틱 멤버
  //static class A{} : 스태틱 클래스
  //static int a; : 스태틱 멤버
  //static void m1(){} : 스태틱 메서드
  //static {} : 스태틱 블록

  // 2) non-static nested class = inner class
  // => 바깥 클래스의 특정 인스턴스에 종속되는 클래스.
  class B {}
  // -> 다른 인스턴스 멤버
  //class b{} : 인스턴스 클래스
  //int b : 인스턴스 필드
  //void m2() {} : 인스턴스 메서드
  //{} : 인스턴스 블록 

  public static void main(String[] args) {
    // 3) local class
    // => 특정 메서드 안에서만 사용되는 클래스.
    class C {}

    // 4) anonymous class 
    // => 클래스의 이름이 없다.
    // => 클래스를 정의하는 동시에 인스턴스를 생성해야 한다.
    // => 클래스 이름이 없기 때문에 생성자를 정의할 수 없다.
    // => 만약 인스턴스의 값을 초기화시키기 위해 복잡한 코드를 작성해야 한다면,
    //    인스턴스 블록에 작성하라!
    // => 단 한 개의 인스턴스만 생성해서 사용할 경우 익명 클래스를 적용한다.
    // => 문법
    //      new 수퍼클래스() {클래스 정의}
    //      new 인터페이스() {클래스 정의}
    
    Object obj = new Car() {
      //Object 클래스를 상속받은 익명 클래스를 만들고,
      //f1()을 추가한다.
      public void f1() {
        System.out.println(this.model + " : 달려라! 달려라!");
      }
    }; // new 명령은 인스턴스를 생성하는 명령이기 때문에 문장 끝에 세미콜론(;)을 붙여야 한다.
  }

}







