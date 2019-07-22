//인터페이스 레퍼런스와 인스턴스의 관계
package ch18.a2;

public class Test01 {

  public static void main(String[] args) {

    // 인터페이스 레퍼런스 선언
    // -> Spec 인터페이스를 구현한 클래스의 인스턴스 주소를 저장하겠다는 뜻이다.
    // -> Spec 규칙에 따라 작성한 클래스의 객체를 담겠다는 뜻이다.
    // -> Spec 객체를 담겠다는 의미다.
    Spec tool;
    
    // ToolA 클래스는 Spec 인터페이스의 규칙에 따라 만든 클래스이기 때문에
    // 이 클래스의 인스턴스 주소를 tool 레퍼런스에 저장할 수 있다.
    tool = new ToolA();
    
    //ToolB 클래스도 ToolA와 마찬가지로 Spec 구현체(implementer; 인터페이스 규칙에 따라만든(상속받은) 클래스)이기 때문에
    //해당 객체도 저장할 수 있다.
    tool = new ToolB();
    
    //String 클래스는 Spec 구현체가 아니기 때문에 해당 객체를 레퍼런스에 저장할 수 없다.
//    tool = new String("Hello");
  }
  
  static void use(Spec tool) {
    // tool 레퍼런스가 가리키는 인스턴스에 대해 A 규칙에 정의된 메서드를 호출한다.
    // 그러면 해당 인스턴스의 클래스를 찾아 메서드를 호출한다.
    tool.m1();
  }

}







