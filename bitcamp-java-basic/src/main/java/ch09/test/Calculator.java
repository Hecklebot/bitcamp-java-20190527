package ch09.test;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {
  //개별적으로 관리되어야 할 값은 인스턴스 필드에 저장해야 한다.
  int result;
  
  //인스턴스 변수를 쓰지 않는 메소드는 static으로 선언할 것
//static int result;
  
//static void plus(Calculator that, int a){
//  that.result += a;
//}
  
  void plus(int a) {
    //모든 인스턴스 메서드는 호출될 때 넘겨받은 인스턴스 주소를
    //내부에서 미리 생성한(built-in) 로컬변수 this에 보관한다.
    //static 메소드에선 this를 쓸 수 없다. (생성되지 않음)
    this.result += a;  //call by reference: heap영역의 인스턴스의 값을 변경
    //this는 참조변수의 주소를 의미한다. -> 즉, 참조변수를 주지 않으면 쓸 수 없다.(인스턴스 메소드 전부)
  }
  
  void minus(int a) {
    this.result -= a;
  }
  
  void multiple(int a) {
    this.result *= a;
  }
  
  void divide(int a) {
    this.result /= a;
  }
  
}









