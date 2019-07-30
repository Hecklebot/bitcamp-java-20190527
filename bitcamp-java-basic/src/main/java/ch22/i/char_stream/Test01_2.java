// 파일에 출력하기: StringBuffer API를 사용해서
package ch22.i.char_stream;

import java.io.FileWriter;

public class Test01_2 {
  public static void main(String[] args) {
    // 문자를 '가'에서부터 100자 출력한다.

    // 1. 문자를 출력할 파일 출력 객체 준비
    try (FileWriter out = new FileWriter("temp/data.txt")) {
      for (int i = 0, ch = '가'; i < 100; i++, ch++) {
        out.write((char) ch);
      }
      System.out.println("출력 완료");
    } catch (Exception e) {
      System.out.println("출력 중 오류");
      e.printStackTrace();
    }

  }
}
