package design_pattern.observer.after;

public class Test06 {

  public static void main(String[] args) {
    
    // 예) 2월 30일 - 자동차 시동을 걸 때 엔진 오일 유무를 검사하는 기능을 추가 
    Car car = new Car();

    car.addObserver(new SafeBeltCarObserver());
    car.addObserver(new EngineOilCarObserver());
    car.addObserver(new BreakOilCarObserver());
    car.addObserver(new LightOffCarObserver());
    car.addObserver(new SunRoofCloseCarObserver());
    car.start();
    car.run();
    car.stop();
    
  }

}








