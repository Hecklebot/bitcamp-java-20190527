//인터페이스와 디폴트 메서드
package ch18.g5;

import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;
import ch18.g2.Printer;

public class Test01 {
  public static void main(String[] args) {
    //신규 프로젝트에서는 필요한 워터마킹 기능을 기존 클래스에 영향을 주지 않으면서 기존 규격에 포함 시키는 방법
    // -> 디폴트 메서드를 추가하라
    // -> g2 패키지의 Printer 인터페이스에 waterMark()를 디폴트 메서드로 선언하면 된다.
    //신규 규격 프린터 사용하기
    WaterMarkPrinter p1 = new WaterMarkPrinter();
    display(p1, "안녕하세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    System.out.println();
    //기존 규격 프린터 사용하기
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    
    //기존 규격(Printer)에 새 규칙을 추가하더라도, 기존의 작성한 클래스를 그대로 활용할 수 있다.
    //굳이 g4 패키지에서 한 것처럼 어댑터 패턴을 사용할 필요도 없다
    display(p2, "안녕히가세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    System.out.println();

    display(p3, "어서오세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
  }
  
  private static void display(Printer printer, String text, String waterMarkText) {
    //기존 규격을 그대로 사용하면 된다.
    printer.watermark(waterMarkText); //기존 규격에 새로 추가한 메서드이다.
    printer.print(text);
    printer.watermark(waterMarkText); //기존 규격에 새로 추가한 메서드이다.
  }
  
}
