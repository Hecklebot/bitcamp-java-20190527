// 바이트 배열 객체에 출력하기: 직접 바이트 배열을 다루는 경우
package ch22.i.byte_stream;

import java.io.FileOutputStream;

public class Test01_2 {
  public static void main(String[] args) {
    // 1~100 중 짝수만 출력해보자
    
    // 1. 값을 출력할 파일 출력 객체 준비
    try (FileOutputStream out = new FileOutputStream("temp/data.bin")) {
    // 2. 짝수를 파일에 출력한다.
      for (int i = 0; i < 100; i++) {
        if (i % 2 == 0) {
          out.write(i);
        }
      }
      System.out.println("출력 완료");
    } catch (Exception e) {
      System.out.println("파일 출력 중 예외발생");
      e.printStackTrace();
    }

  }
}
