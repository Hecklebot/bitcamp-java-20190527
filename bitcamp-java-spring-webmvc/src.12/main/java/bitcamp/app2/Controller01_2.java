// View Resolver 교체 - InternalResourceViewResolver 사용하기
package bitcamp.app2;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("/c01_2")
public class Controller01_2 {

  // 테스트:
  //   http://localhost:8080/java-spring-webmvc/app2/c01_2/h1
  @GetMapping("h1") 
  public String handler1(Map<String, Object> map) {
    
    map.put("name", "홍길동");
    map.put("age", 10);
    
    // ViewResolver?
    // => 페이지 컨트롤러가 리턴한 뷰 이름에 해당하는 뷰 컴포넌트를 찾아 실행하는 역할
    // => ResourceBundleViewResolver
    //    - *.properties 에서 뷰 이름에 해당하는 컴포넌트의 URL을 찾는다.
    // => InternalResourceViewResolver
    //    - 미리 지정된 접두사, 접미사를 사용하여 뷰 이름으로 컴포넌트의 URL을 완성한다.
    //
    // View?
    // => 템플릿 엔진을 실행하여 실제 클라이언트로 보낼 컨텐츠를 생성한다.
    // => FreeMarker, JSP/JSTL, Tiles, RSS/Atom, PDF, Excel 등의 
    //    엔진을 이용하여 컨텐츠를 생성하는 뷰를 제공한다.
    //
    // ViewResolver 교체
    // InternalResourceViewResolver를 사용하여 
    // JSP URL의 접두어와 접미사를 미리 설정해 둘 수 있어, URL을 지정하기 편리하다.
    // => 교체 방법은 XML에서 설정하는 방법과 Java Config로 설정하는 방법이 있다.
    //    자세한 것은 App2Config 클래스를 참고하라
    //
    // ViewResolver 실행 과정
    // => 페이지 컨트롤러는 작업을 클라이언트가 요청한 작업을 실행한 후
    //    그 결과를 출력할 뷰의 이름을 리턴한다.
    // => 프론트 컨트롤러는 request handler가 리턴한 URL을 view resolver에게 전달한다.
    // => view resolver는 자신의 정책에 맞춰서 뷰 URL을 처리한다.
    // => InternalResourceViewResolver의 경우 
    //    request handler가 리턴한 URL 앞, 뒤에 
    //    접두사와 접미사를 붙여 JSP를 찾는다.
    //    예를 들어 "c01_2/h1"을 리턴하면 
    //    최종 JSP URL은 "/WEB-INF/jsp2/c01_2/h1.jsp"가 된다.
    //
    return "c01_2/h1";
  }

  // 테스트:
  //   http://localhost:8080/java-spring-webmvc/app2/c01_2/h2
  @GetMapping("h2") 
  public void handler2(Model model) {

    model.addAttribute("name", "홍길동2");
    model.addAttribute("age", 20);
    
    // InternalResourceViewResolver를 사용하는 경우,
    // request handler가 값을 리턴하지 않으면 
    // request handler의 URL을 뷰 이름으로 사용한다.
    // 즉, 이 핸들러의 URL은 "/c01_1/h2" 이다.
    // InternalResourceViewResolver는 바로 이 URL을 사용하여 다음과 같이 최종 URL을 만든다.
    // 따라서 최종 JSP URL은 "/WEB-INF/jsp2/" + "/c01_1/h2" + ".jsp" 이다.
    // => "/WEB-INF/jsp2/c01_1/h2.jsp"
    //
    // 그래서 실무에서는 이 방법을 많이 사용한다.
  }
  
  // 테스트:
  //   http://localhost:8080/java-spring-webmvc/app2/c01_2/h3
  @GetMapping("h3") 
  public Map<String,Object> handler3() {
    
    HashMap<String,Object> map = new HashMap<>();
    map.put("name", "홍길동3");
    map.put("age", 30);
    
    // Map 객체에 값을 담아 리턴하면 
    // 프론트 컨트롤러는 Map 객체에 보관되어 있는 값들을 ServletRequest 보관소로 옮긴다.
    // 그리고 view URL은 request handler의 URL을 사용한다.
    return map;
  }
  
  // 테스트:
  //   http://localhost:8080/java-spring-webmvc/app2/c01_2/h4
  @GetMapping("h4") 
  public ModelAndView handler4() {
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("name", "홍길동4");
    mv.addObject("age", 40);
    mv.setViewName("c01_2/h4");
    // ModelAndView 객체에 값을 담아 리턴하면 
    // 프론트 컨트롤러는 ModelAndView 객체에 보관되어 있는 값들을 ServletRequest 보관소로 옮기고,
    // 설정된 뷰 이름을 ViewResolver에게 넘긴다.
    return mv;
  }
  
  // 테스트:
  //   http://localhost:8080/java-spring-webmvc/app2/c01_2/h4
  @GetMapping("h5") 
  public ModelAndView handler5() {
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("name", "홍길동5");
    mv.addObject("age", 50);
    // ModelAndView 객체에 값을 담아 리턴하면 
    // 프론트 컨트롤러는 ModelAndView 객체에 보관되어 있는 값들을 ServletRequest 보관소로 옮기고,
    // 뷰 이름을 지정하지 않으면 requestHandler의 path를 ViewResolver에게 넘긴다.
    return mv;
  }
  
}













