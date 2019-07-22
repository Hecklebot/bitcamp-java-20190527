// 인터페이스 사용 전
package ch18.a1;

// 규칙에 따라 클래스를 만들면 도구를 사용하는 입장에서 
// 일관된 코딩을 할 수 있어 유지보수에 좋다.
public class ToolA {

  //각각의 클래스는 자신만의 이름으로 메서드를 제공한다.
  public void m1() {
    System.out.println("ToolA.m1()");
  }
}
