package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input {
  
  
  //Input에 연결된 Scanner 객체를 각 Input마다 개별적으로 관리하기 위해 인스턴스 필드로 선언한다.
//public static Scanner keyScan = new Scanner(System.in); //이렇게 하면 키보드에서밖에 읽을 수 없다. 재사용성이 낮은 코드
  private Scanner keyScan;
  
  public Input(Scanner keyScan) {
    //Input 클래스에 있는 메서드를 사용하는데 필요한 keyScan 변수를 준비한다. 파라미터로 다양한 종류의 스캐너를 받아서
    //단, 인스턴스를 생성할 때 반드시 Scanner 객체를 넘기도록 강제하기 위해 생성자에 파라미터를 선언한다.
    this.keyScan = keyScan;
  }
  
  
  //기존의 스태틱 메서드를 인스턴스 메서드로 전환한 이유
  //-> 각 Input마다 Scanner를 구분해서 다루기 위함이다.
  public int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(this.keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  public Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-01-01과 같이 입력하세요.");
      }
    }
  }

  public String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

}
