// v59_2: WebApplicationInitializer를 사용하여 DispatcherServlet 배치하기
package com.eomcs.lms;

// 작업:
// => com.eomcs.lms.controller를 com.eomcs.lms.web으로 이름 변경
// => com.eomcs.lms.config.WebConfig 생성
// => WebApplicationInitializer 생성
// => /WEB_INF/web.xml 변경
//  - DispatcherServlet 배치 정보 제거
//  - ContextLoaderListener 배치 정보 제거
//  - ContextLoaderListener가 사용하는 컨텍스트 파라미터 제거
// => AppConfig, DatabaseConfig, MybatisConfig에서 @Configuration 제거
//  - AppWebApplicationInitializer 에서 이 Java Config 클래스를 직접 지정
// => com.eomcs.lms.config.AppConfig 변경
//  - DispatcherServlet의 IoC 컨테이너가 관리하는 com.eomcs.lms.web 패키지는 제외한다.
// => 기존의 JSP 폴더를 /WEB-INF/jsp 로 이동
// => 페이지 컨트롤러의 리턴 값을 InternalResoueceViewResolver에 맞게 변경한다.
//
// dummy 클래스!
// => 기존 버전에서 계속 존재했던 클래스라서 그대로 둠.
// => 단지 버전의 목표에 대한 설명을 기록하기 위해 존재함.
// => 프로젝트에서 사용되지 않음!
//
public class App {
}







