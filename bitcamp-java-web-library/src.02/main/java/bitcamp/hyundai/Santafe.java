package bitcamp.hyundai;

import bitcamp.Car;

public class Santafe implements Car {
  
  @Override
  public void run() {
    System.out.println("Santafe.run() 호출됨");
  }
}
