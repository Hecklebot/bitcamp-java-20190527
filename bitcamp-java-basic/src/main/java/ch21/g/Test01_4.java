//예외처리 연습 - 메서드의 예외를 메인에서 처리하기
package ch21.g;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test01_4 {

  static FileReader in;
  public static void main(String[] args) {
    
    //여기서 예외가 발생하면 main()의 호출자인 JNM에게 전달될 것이다.
    //이건 되긴 하는데 main()의 호출자는 JVM이기 때문에 어차피 예외가 발생한다.
    
    try {
      m1();
    } catch(FileNotFoundException e) {
      System.out.println("파일이 존재하지 않습니다.");
    }
    
    
  }
  static void m1() throws FileNotFoundException {
    in = new FileReader("build2.gradle"); //컴파일 오류
    
  }
}
