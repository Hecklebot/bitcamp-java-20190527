// 예외처리 연습 - 예외처리 안했을 때
package ch21.g;

public class Test02_1 {

  public static void main(String[] args) {

    System.out.println(100 / 0);
    // RuntimeException에 대해서는 컴파일러가 예외처리를 요구하지 않는다.
    // 다만 예외가 발생하면 호출자에게 전달한다.
    // 여기서 예외가 발생하면 main()의 호출자인 JVM에게 전달한다.
    
    System.out.println("종료하고싶은데..");
  }

}
