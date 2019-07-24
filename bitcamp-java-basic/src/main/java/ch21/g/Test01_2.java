// 예외처리 연습 - try ~ catch ~로 예외처리
package ch21.g;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test01_2 {

  public static void main(String[] args) {
    FileReader in;
    try {
      in = new FileReader("build.gradle"); // 컴파일 오류
    } catch (FileNotFoundException e) {
      System.out.println("해당 파일을 찾을 수 없습니다.");
    }

  }

}
