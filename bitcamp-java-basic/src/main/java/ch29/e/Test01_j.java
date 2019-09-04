// 프로퍼티 값 설정 - 배열 프로퍼티 값 설정하기
package ch29.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test01_j {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    
    Car car1 = (Car) iocContainer.getBean("car");
    Car car2 = (Car) iocContainer.getBean("car");
    
    System.out.println(car1);
    System.out.println("-------------------------------------------------------------------------------------------");
    System.out.println(car2);
  }
}






