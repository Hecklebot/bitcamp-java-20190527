package bitcamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import bitcamp.app2.Controller04_1_Interceptor1;
import bitcamp.app2.Controller04_1_Interceptor2;
import bitcamp.app2.Controller04_1_Interceptor3;
import bitcamp.app2.Controller04_1_Interceptor4;

// Apache Commons Fileupload 사용
@ComponentScan("bitcamp.app2")
// => 지정된 패키지에서 @Component, @Controller 등이 붙은 클래스에 대해 인스턴스를 생성한다.
@EnableWebMvc
// => Web MVC 관련 객체를 등록하고 설정한다.
// => WebMvcConfigurer 구현체를 찾아 메서드를 호출한다.
public class App2Config implements WebMvcConfigurer {

  // DispatcherServlet의 ViewResolver를 교체하기
  // 1) XML
  // <bean id="viewResolver"
  // class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  // <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
  // <property name="prefix" value="/WEB-INF/jsp/"/>
  // <property name="suffix" value=".jsp"/>
  // </bean>

  // 2) Java Config 설정
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/jsp2/", ".jsp");
    return vr;
  }

  // @MatrixVariable 처리 활성화
  // => 이 작업을 수행하려면 MVC 관련 설정 작업을 수행할 수 있도록
  // WebMvcConfigurer 인터페이스를 구현해야 한다.
  // => 그리고 다음 메서드를 오버라이딩 하여 기존 설정을 변경한다.
  //
  // DispatcherServlet이 MVC 관련 설정을 처리하는 과정
  // => WebMVC 설정을 활성화했는지 검사한다.
  // => 활성화되었다면, IoC 컨테이너가 관리하는 객체중에서
  // WebMvcConfigurer 구현체를 찾는다.
  // 관리하는 객체?
  // - IoC 컨테이너에 들어있는 객체
  // - @Component, @Service, @Controller, @RestController, @Repository
  // 애노테이션이 붙은 클래스들은 IoC 컨테이너가 자동으로 객체를 생성하여 보관한다.
  // - 하지만 WebMvcConfigurer 구현제는 Java Config 클래스이기 때문에
  // 일반 객체로 표시하지 말고, @Configuraion을 붙여,
  // Java Config로 명확하게 하는 것이 유지보수에 좋다.
  // 물론 @Configuration이 붙은 클래스도 객체가 생성되어 IoC 컨테이너에 보관되는 것은 같다.
  //
  // => 객체를 찾았으면 WebMvcConfigurer 규칙에 따라 메서드를 호출하여
  // 설정을 추가하거나 변경한다.
  // => WebMVC 설정을 활성화하지 않으면 WebMvcConfigurer 구현체가 있더라도 무시한다.
  // => WebMVC 설정을 활성화시키는 방법
  // 1) XML 설정
  // <mvc:annotation-driven/>
  // 2) Java Config 설정
  // @EnableWebMvc 표시
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper helper = new UrlPathHelper();
    helper.setRemoveSemicolonContent(false);

    // DispatcherServlet의 MVC Path 관련 설정을 변경한다.
    configurer.setUrlPathHelper(helper);
  }

  // 이 설정을 사용하는 프론트 컨트롤러에 적용할 인터셉터 적용하기
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // 1) 모든 요청에 대해 실행할 인터셉터 등록하기
    // => 인터셉터를 적용할 URL을 지정하지 않으면 현재 프론트 컨트롤러의 모든 요청에 대해 적용된다.
    registry.addInterceptor(new Controller04_1_Interceptor1());

    // 2) 특정 요청에 대해 실행할 인터셉터 등록하기
    // => 패턴: /c04_1/*
    //  적용 가능:
    //      /c04_1/h1
    //      /c04_1/x
    //  적용 불가:
    //      /x
    //      /c03_1/x
    //      /c04_1/a/x
    //      /c04_1/a/b/x
    // => 하위 디렉토리도 포함하고 싶다면 /c04_1/**로 적용하라
    registry.addInterceptor(new Controller04_1_Interceptor2()).addPathPatterns("/c04_1/*");
    registry.addInterceptor(new Controller04_1_Interceptor3()).addPathPatterns("/c04_1/**");
    
    // 3) 특정 요청에 대해 인터셉터 실행 제외하기
    // => 패턴: /c04_1/a/**
    //  적용 가능:
    //      /c04_1/h1
    //      /c04_1/x
    //  적용 불가:
    //      /c04_1/a/x
    //      /c04_1/a/b/x
    registry.addInterceptor(new Controller04_1_Interceptor4()).addPathPatterns("/c04_1/**")
        .excludePathPatterns("/c04_1/a/**");
  }
}


