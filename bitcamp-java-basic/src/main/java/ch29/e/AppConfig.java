package ch29.e;

import java.sql.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="ch29.e")
public class AppConfig {
  
  @Bean
  private BlackBox blackBox() {
    BlackBox blackBox = new BlackBox();
    blackBox.setMaker("현대모비스");
    blackBox.setModel("아이나비");
    return blackBox;
  }
  
  @Bean
  private Tire[] tires() {
    Tire tire = new Tire();
    Tire[] tires = new Tire[4];
    tire.setMaker("넥센타이어");
    tire.setWidth(16);
    tire.setRatio(20);
    tire.setWheel(4);
    
    tires[0] = tire;
    tires[1] = tire;
    tires[2] = tire;
    tires[3] = tire;
    
    return tires;
  }
  
  @Bean
  private Car car(BlackBox blackBox, Tire[] tires) {
    Car car = new Car();
    
    car.setMaker("현대");
    car.setModel("소나타");
    car.setCc(2000);
    car.setValve(4);
    car.setAuto(true);
    car.setCreatedDate(Date.valueOf("2018-1-1"));
    car.setBlackBox(blackBox);
    car.setTires(tires);
    
    return car;
  }
}
