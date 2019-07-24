// 예외처리 연습 - 호출자에게 전달하기
package ch21.g;

public class Test02_3 {

  public static void main(String[] args) throws RuntimeException {

      System.out.println(100 / 0);
      // RuntimeException 예외가 발생되는 코드를 작성할 경우
      // 메서드 선언부에 굳이 throws를 포함할 필요는 없다.
      // 그런 불편함을 없애기 위해 만든 예외가 RuntimeException인데 굳이 귀찮게?

  }
}
