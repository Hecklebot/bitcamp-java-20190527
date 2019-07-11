// 인스턴스 멤버 : 인스턴스 초기화 블럭
package ch10;


class My2 {
  //1) 인스턴스 필드
  int a = 100;
  //int a <- 필드 선언 문장
  //a =100; <- 필드의 초기화 문장
  //2) 인스턴스 블록
  //=> 인스턴스 변수를 모두 생성하고, 초기화 문장까지 실행한 후 "인스턴스 블록"이 실행된다.
  //=> 인스턴스 블록은 선언된 순서대로 실행된다.
  //=> 인스턴스 블록도 인스턴스 메서드와 마찬가지로 인스턴스의 주소를 담고 있는 this 라는 변수가 
  //   내장되어 있다. 
  {
    System.out.println("인스턴스 블록 1 실행!");
    this.a = 200; //(this 생략가능)
  }
  
  {
    System.out.println("인스턴스 블록 2 실행");
    this.a = 300; //(this 생략가능)
  }
  
  //4) 인스턴스 메서드
  //=> 인스턴스 레퍼런스를 통해 호출한다.
  void m1() {
    System.out.println("인스턴스 메서드: m1()");
  }
}

public class Test02_2 {
  public static void main(String[] args) {
    My2 obj = new My2();
    System.out.println(obj.a);
  }
}
















