package ch29.j;

import org.springframework.context.annotation.ComponentScan;

// 지정된 패키지를 뒤져서 @Component, @Service, @Repository, @Controller, @RestController 를 찾아
// 인스턴스를 자동 생성한다.
// 또한, @Configuration이 붙은 클래스를 발견하면 Java config 클래스로 인식한다.
//  -> Java config 클래스는 항상 인스턴스를 생성한 후 관련된 설정대로 준비한다.
@ComponentScan({"ch29.j","ch29.j2"})
public class AppConfig1 {

}
