// v55_1: Servlet 컨테이너 도입하기
package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;
import com.eomcs.util.RequestMappingHandlerMapping.RequestHandler;


@WebServlet("/*") // 어떤 요청이 들어오든 이 클래스를 실행하라
public class App implements Servlet {

  // Log4j의 로그 출력 도구를 준비한다.
  private static final Logger logger = LogManager.getLogger(App.class);

  ServletConfig config;
  ApplicationContext appCtx;
  RequestMappingHandlerMapping handlerMapping;

  @Override
  public void init(ServletConfig config) throws ServletException {

    this.config = config;

    // 톰캣 서버가 이 객체를 사용하기 전에
    // 이 객체가 작업에 필요한 자원들을 준비할 수 있도록 이 메서드를 호출한다.
    // 기존의 생성자가 하던 일을 init()이 대신 한다.
    appCtx = new AnnotationConfigApplicationContext(AppConfig.class);

    // Spring IoC 컨테이너에 들어있는(Spring IoC 컨테이너가 생성한) 객체 알아내기
    String[] beanNames = appCtx.getBeanDefinitionNames();
    logger.debug("[Spring IoC 컨테이너 객체들]-------------------------------------------------");
    for (String beanName : beanNames) {
      logger.debug(
          String.format("%s (%s)", appCtx.getBean(beanName).getClass().getSimpleName(), beanName));
    }
    logger.debug("-----------------------------------------------------------------------------");

    handlerMapping = createRequestMappingHandlerMapping();
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    // 클라이언트가 요청한 정보를 자세하게 봅기 위해 파라미터 객체를 원래의 타입으로 형변환한다.
    HttpServletRequest httpReq = (HttpServletRequest) request;
    String command = httpReq.getPathInfo();
    logger.info(command);
    logger.info(httpReq.getQueryString());

    try {
      RequestHandler requestHandler = handlerMapping.getRequestHandler(command);

      response.setContentType("text/html;charset=UTF-8");
      
      if (requestHandler != null) {
        // 클라이언트 요청 처리하기 위해 메서드를 호출한다.
        requestHandler.method.invoke(requestHandler.bean, request, response);

      } else {
        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>해당 명령을 처리할 커맨드가 없습니다.</h1></body></html>");
        logger.info("실패 - " + command);
      }

    } catch (Exception e) {
      PrintWriter out = response.getWriter();
      out.println("<html><body><h1>요청 처리 중 오류 발생</h1></body></html>");

      logger.info("클라이언트 요청 처리 중 오류 발생");
      StringWriter out2 = new StringWriter();
      e.printStackTrace(new PrintWriter(out2));
      logger.debug(out2.toString());
    }
  }

  @Override
  public void destroy() {
    // 톰캣 서버가 종료되기 전에
    // 이 객체가 준비한 자원들을 해제할 수 있도록 이 메서드를 호출한다.

  }

  @Override
  public String getServletInfo() {
    // 톰캣 서버가 관리자 페이지에 이 메서드의 리턴값을 출력한다.
    // 출력할 내용은 애플리케이션에 대한 소개를 담는다.
    return "수업 관리 시스템";
  }

  @Override
  public ServletConfig getServletConfig() {
    // 이 객체를 실행하면서 이 객체의 실행 환경정보를 리턴한다.
    // 리턴값은 init()로 받은 파라미터값이다.
    // 따라서 init()에서 파라미터 값을 잘 보관해둬야 한다.
    return this.config;
  }


  private RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();

    // 객체 풀에서 @Component가 붙은 객체 목록을 꺼낸다.
    Map<String, Object> components = appCtx.getBeansWithAnnotation(Component.class);
    logger.trace("[요청 핸들러]====================================================");

    // 객체 안에 선언된 메서드 중에서 @RequestMapping이 붙은 메서드를 찾아낸다.
    Collection<Object> objList = components.values();
    objList.forEach(obj -> {

      Method[] methods = null;
      if (AopUtils.isAopProxy(obj)) { // 원본이 아니라 proxy라면
        // 프록시 객체의 클래스가 아니라 원본 객체의 클래스 정보를 가져온다.
        try {
          Class<?> originClass = (Class<?>) obj.getClass().getMethod("getTargetClass").invoke(obj);
          methods = originClass.getMethods();
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        // 원본 객체일 경우
        // 원본 객체의 클래스로부터 메서드를 가져온다.
        methods = obj.getClass().getMethods();
      }
      // 객체에서 메서드 정보를 추출한다.
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if (anno == null) {
          continue;
        }

        // @RequestMapping이 붙은 메서드를 찾으면 mapping 객체에 보관한다.
        mapping.addRequestHandler(anno.value()[0], obj, m);
        logger.trace(String.format("%s --> %s.%s()", anno.value()[0],
            obj.getClass().getSimpleName(), m.getName()));
      }
    });
    return mapping;
  }
}
