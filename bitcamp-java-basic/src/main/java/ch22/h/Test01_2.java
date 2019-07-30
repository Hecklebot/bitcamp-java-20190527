// 파일 입출력과 예외처리: 예외처리 후
package ch22.h;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test01_2 {
  public static void main(String[] args) {

    FileOutputStream out = null;
    try {
      out = new FileOutputStream("temp3/data.bin");

      out.write(0x55);

    } catch (IOException e) {
      e.printStackTrace();

    } finally {
      try {
        out.close();
      } catch (IOException e) {
        System.out.println("파일 입출력 예외발생");
      }
    }
    
    System.out.println("출력 완료!");
  }
}


