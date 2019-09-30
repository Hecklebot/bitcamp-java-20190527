package bitcamp.config;

import java.io.File;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class App1WebApplicationInitializerImpl 
extends AbstractAnnotationConfigDispatcherServletInitializer {
  
  String uploadTmpDir;
  
  public App1WebApplicationInitializerImpl() {
    uploadTmpDir = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
    System.out.println("업로드 임시 폴더 : " + uploadTmpDir);
  }
  
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {App1Config.class};
  }
  
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app1/*"};
  }
  
  @Override
  protected String getServletName() {
    return "app1";
  }
  
//  Servlet 3.0 파일 업로드 방법  
//  @Override
//  protected void customizeRegistration(Dynamic registration) {
      // Servlet 3.0 API의 파일 업로드를 사용하려면 다음과 같이
      // DispatcherServlet에 설정을 추가해야 한다.
      // 만약 Spring의 방식으로 파일 업로드를 처리하고 싶다면,
      // AppConfig.java의 MultipartResolver를 확인하라!
      // 
//    // DispatcherServlet이 multipart/form-data로 전송된 데이터를 처리하려면
//    // 해당 콤포넌트를 등록해야 한다.
//    System.out.println(System.getProperty("java.io.tmpdil"));
//    registration.setMultipartConfig(new MultipartConfigElement(
//        uploadTmpDir,     // 업로드 한 파일의 임시 보관위치 
//        10000000,   // 최대 업로드 크기(모든 파일) 
//        15000000,   // 요청 전체 데이터의 크기
//        2000000));  // 업로드되고 있는 파일을 디스크에 임시보관할 크기
//  }
}






