// 애플리케이션 예외의 종류: main()과 RuntimeException
package ch21.c;

import java.util.Scanner;

public class Test01_3 {

  public static void main(String[] args) {
    // RuntimeException을 발생시키는 메서드를 호출할 때, 예외처리 코드를 작정하지 않더라도
    // 컴파일러는 오류를 띄우지 않는다.
    // 하지만 RuntimeException 예외를 처리하지 않으면 최종적으로 JVM에게 전달되고 프로그램을 종료하기 때문에
    // 프로그램을 종료하기 싫으면 RuntimeException에 대해서도 예외처리를 해야한다.

    Scanner keyScan = new Scanner(System.in);

    while (true) {
      try {
        // 아래의 코드에서 예외가 발생한다면 RuntimeException이다. 따라서 try catch를 사용하지 않아도 컴파일러가 에러를 띄우진 않는다.
        // 그러나 예외는 예외이기 때문에, 발생하는 즉시 호출자에게 전달하고 main()의 호출자는 JVM이기 때문에 즉시 실행을 멈춘다.
        // 그래서 예외처리가 강제사항이 아닐지라도 try catch처리한 것이다.
        System.out.println("값 1? ");
        int a = Integer.parseInt(keyScan.nextLine());

        System.out.println("값 2? ");
        int b = Integer.parseInt(keyScan.nextLine());

        System.out.println(divide(a, b));
      } catch (RuntimeException e) {
        System.out.println("error");
      }

    }



  }

  static int divide(int a, int b) throws RuntimeException {
    if (b == 0) {
      throw new RuntimeException("0으로 나눌 수 없습니다.");
    }
    return a / b;
  }

}
