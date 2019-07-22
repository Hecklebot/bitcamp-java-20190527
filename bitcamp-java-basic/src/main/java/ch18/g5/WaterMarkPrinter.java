// 신규 프로젝트를 위해 새로 규칙을 추가(watermark())하였고
// 이 클래스는 그 규칙을 구현하였다.
package ch18.g5;

import ch18.g3.Printer2;

//패키지 g3처럼 새 규칙을 추가하기 위해 새 규격을 정의할 필요가 없다.
//"디폴트 메서드" 라는 문법을 사용하면 기존 클래스에 영향을 주지 않고 새 규칙을 추가할 수 있다.
public class WaterMarkPrinter implements Printer2 {
  // Printer 인터페이스에 선언된 규칙을 구현한다.
  @Override
  public void print(String text) {
    System.out.println("WaterMarkPrinter: " + text);
  }
  
  //Printer 인터페이스에 새로 추가한 규칙을 구현한다.
  @Override
  public void watermark(String title) {
    System.out.println("**" + title + "**");
  }
}
