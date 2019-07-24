// 예외처리 연습 - try catch로 직접 처리하기
package ch21.g;

public class Test02_2 {

  public static void main(String[] args) {

    try {
      System.out.println(100 / 0);
      // RuntimeException에 대해서는 컴파일러가 예외처리를 요구하지 않는다.
      // 다만 예외가 발생하면 호출자에게 전달한다.
      // 여기서 예외가 발생하면 main()의 호출자인 JVM에게 전달한다.
    } catch (RuntimeException e) {
      // RuntimeException을 처리하고 싶다면 Exception과 같이 try ~ catch ~를 사용하라
      System.out.println("0으로 나눌 수 없습니다.");

      // 이런 RuntimeException 예외를 개발자는 항상 조심해야 한다.
      // 컴파일러가 알려주지 않기 때문에 개발자가 스스로 판단하여 예외를 직접 처리할 것인지, 호출자에게 전달할 것인지 결정해야 하기 때문이다.
    }


  }
}
