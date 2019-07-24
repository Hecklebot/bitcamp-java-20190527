//예외처리 연습 - 메서드의 예외를 메인에서 처리하기
package ch21.g;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test01_4 {

  static FileReader in;
  public static void main(String[] args) {
    
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
