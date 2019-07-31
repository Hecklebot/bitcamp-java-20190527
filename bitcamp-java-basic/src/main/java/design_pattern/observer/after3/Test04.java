package design_pattern.observer.after3;

public class Test04 {

  public static void main(String[] args) {
    
    // 예) 3월 2일 - 자동차 시동을 걸 때 브레이크 오일 유무를 검사하는 기능을 추가
    Car car = new Car();
    
    car.addObserver(new SafeBeltCarObserver());
    car.addObserver(new EngineOilCarObserver());
    car.addObserver(new BreakOilCarObserver());
    car.start();
    car.run();
    car.stop();
    
  }

}








