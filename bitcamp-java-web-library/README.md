# bitcamp-java-web-library
bitcamp-java-spring-webmvc 프로젝트에서 사용할 라이브러리(.jar)를 생성한다.

## src.01 : 웹 애플리케이션을 시작할 때, 보고 받기

- ServletContainerInitializer 구현체를 만든다.
- /META-INF/services/javax.servlet.ServletContainerInitializer 파일 생성
- 해당 파일에 구현체 클래스의 이름을 등록한다.
- gradle build를 실행하여 .jar 파일을 생성한다.
- .jar 파일을 웹 애플리케이션 프로젝트에 임포트 한 다음 웹 서블릿 컨테이너를 실행하라.
- 콘솔 창에 onStartup()이 호출된 것을 확인할 수 있다.

## src.02 : 보고 받을 때, 특정 타입의 클래스 목록도 함께 보고 받기

- onStartup()이 호출될 때 특정 타입의 클래스 목록을 받을 수 있다.
- 클래스의 @HandlesType(타입.class) 애노테이션을 붙인다.
- 그리고 onStartup()의 types 파라미터 값을 사용한다.

## src.03 : 프로그램 코드를 서블릿을 등록하기

- ServletContainerInitializerImpl 변경

## src.04 : 라이브러리 외부에서 만든 객체를 실행시키기

- MyWebInitializer 인터페이스 생성
- ServletContainerInitializerImpl 변경
  - MyWebInitializer 구현체 생성 후 지정된 메서드를 호출한다.
