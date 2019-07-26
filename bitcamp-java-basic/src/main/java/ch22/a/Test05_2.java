// java.io.File 클래스 : 파일 생성
package ch22.a;

import java.io.File;
import java.io.IOException;

public class Test05_2 {

  public static void main(String[] args) throws Exception {
    
    
    // 디렉토리가 존재하지 않으면 파일을 생성하지 못함.
    // => 예외 발생!
    // => 디렉토리가 자동으로 생성되지 않는다.
    try {
    File file = new File("temp3/test.txt");
    if (file.createNewFile()) {
      System.out.println("파일이 생성됨.");
    }
    } catch(IOException e) {
      
      System.out.println("파일을 생성하지 못함.");
    }
  }
}





