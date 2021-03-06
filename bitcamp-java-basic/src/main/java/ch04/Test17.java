// 조건(삼항) 연산자 => 조건 ? 표현식1 : 표현식2
//             cond(ition) ? expr(ession) : expr(ession);
// => 조건이 참이면 표현식1의 리턴 값이 놓이고, 거짓이면 표현식2의 리턴 값이 놓인다.
//
// 문장(statement)?
// => 여러 개의 연산자나 표현식으로 이루어진 한 명령이다.
// => 보통 세미콜론(;)으로 끝낸다.
// 
// 표현식?
// => 문장 중에서 리턴 값이 있는 문장이다.
// 
package ch04;

public class Test17 {
  public static void main(String[] args) {
    int age = 20;
    String result = (age >= 19) ? "성인" : "미성년";
    System.out.println(result);
    System.out.println((age >= 19) ? "성인" : "미성년");
    System.out.println((age >= 65) ? "성인" : false);
    
    //조건 ? 표현식 : 표현식
    //표현식이 아닌 것은 올 수 없다.
    //(age >=19) ? System.out.println("성인") : System.out.println("미성년"); //컴파일 에러
    
    //표현식(expression)?
    // -> 값을 리턴(표현)하는 문장(statement)을 의미
    //(age >= 19) ? m1(): m1(); // 컴파일 오류
    int b = (age >= 19) ? m2(): 100 + 23 - 4; // m2()는 0이란 정수'값'을 리턴하기 때문에 에러가 나지 않는다.
    System.out.println(b); //age=20이기 때문에 true인 m2(), 즉 0을 리턴
  }
  
  static void m1() {
    
  }
  
  static int m2() {
    return 0;
  }
  
}

















