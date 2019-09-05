// Java config - @PropertySource 사용법
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.SpringUtils;

public class Test05 {
  public static void main(String[] args) {
    
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig5.class);
    
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
  }
}






