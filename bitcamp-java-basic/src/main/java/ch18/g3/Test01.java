package ch18.g3;

import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;
import ch18.g4.Printer2Adapter;

public class Test01 {
  public static void main(String[] args) {
    //신큐 프로젝트에서는 워터마킹 기능이 있는 프린터를 사용한다.
    
    //신규 규격 프린터 사용하기
    WaterMarkPrinter p1 = new WaterMarkPrinter();
    display(p1, "안녕하세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    System.out.println();
    //기존 규격 프린터 사용하기
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    
    //안타깝게도 기존 프린터는 새 프린터 규격에 맞지 않아서, 출력하는데 사용할 수 없다.
    //해결책은?
    // -> 어댑터에 꽂아 사용해보자
    display(new Printer2Adapter(p2), "안녕히가세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    System.out.println();
    display(new Printer2Adapter(p3), "어서오세요", "bitcamp bitcamp bitcamp bitcamp bitcamp");
  }
  
  private static void display(Printer2 printer, String text, String waterMarkText) {
    printer.watermark(waterMarkText);
    printer.print(text);
    printer.watermark(waterMarkText);
  }
  
}
