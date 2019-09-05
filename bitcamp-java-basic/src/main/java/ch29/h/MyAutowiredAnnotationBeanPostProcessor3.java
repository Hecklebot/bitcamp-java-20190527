package ch29.h;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MyAutowiredAnnotationBeanPostProcessor3 {
  HashMap<Class<?>, List<Object>> beans = new HashMap<>();
  
  HashMap<Class<?>, List<AutowiredMethod>> autowiredMethods = new HashMap<>();
  
  public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
    
    // 파라미터로 입력받은 객체를 beans에 저장
    this.addBean(bean.getClass(), bean);
    
    // 저장한 객체의 모든 public 메서드를 호출
    Method[] methods = beans.getClass().getMethods();
    
    // 메서드 중에 @Autowired가 붙은 메서드 찾기(setBlackBox)
    for(Method m : methods) {
      Autowired anno = m.getAnnotation(Autowired.class);
      if(anno ==  null) {
        continue;
      }
      
      // Autowired가 붙은 메서드의 파라미터 타입 찾기(파라미터는 한 개라 가정)
      // getParameter는 파라미터의 클래스를 리턴?
      // .getType()를 붙여 파라미터 정보를 찾는다.
      Class<?> paramType = m.getParameters()[0].getType();
      
      // 세터가 원하는 파라미터가 beans에 있는지 확인한다.
      if(beans.get(paramType) != null) { 
        // 세터가 원하는 파라미터가 있다면,
        // (beans에 BlackBox가 준비되어 있다면)
        // setter를 실행한다.
        m.invoke(bean, beans.get(paramType));
      } else {
        // 세터가 원하는 파라미터가 beans에 없다면
        // 일단 임시 저장소에 메서드 정보를 기록한다.
        addAutowiredMethods(paramType.getClass(), new AutowiredMethod(bean, m));
      }
      
    }
    return bean;
  }
  
  private void addAutowiredMethods(Class<?> type, AutowiredMethod autowiredMethod) {
    // autowiredMethods에서 파라미터로 받은 클래스정보를 가진 밸류를 찾아 list에 저장
    List<AutowiredMethod> methods = autowiredMethods.get(type);
    
    // 없으면 새 arrayList 만들어서 임시 저장소에 저장
    if(methods == null) {
      methods = new ArrayList<AutowiredMethod>();
      autowiredMethods.put(type, methods);
    }
    // 있으면 일단 리스트에 저장
    methods.add(autowiredMethod);
    autowiredMethods.put(type, methods); // ?
  }
  
  private void callAutowiredMethod(Object paramValue) {
    List<AutowiredMethod> setters = autowiredMethods.get(paramValue.getClass());
    
    if(setters == null) {
      return;
    }
    
    for(AutowiredMethod setter : setters) {
      try {
        setter.method.invoke(setter.object, paramValue);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
  }
  
  
//  private boolean isParametered(Class<?> type) {
//    // beans에서 type에 해당하는 값이 있으면 true 없으면 false
//    return beans.get(type) != null ? true : false;
//  }
  
//  private Object getDependency(Class<?> type) {
//    // beans에서 type 키값을 사용하는 값(메서드)을 꺼내서 리턴
//    return beans.get(type);
//  }
  
  private void addBean(Class<?> type, Object bean) {
    List<Object> objList = beans.get(type); // 객체 저장소에서 리스트를 가져옴
    if(objList == null) { // 가져올 수 없으면
      objList = new ArrayList<>(); // 새 리스트를 만들고,
      beans.put(type, objList); // 객체 저장소에 빈 리스트를 넣는다.
    }
    objList.add(bean); // 리스트에 파라미터로 받은 객체를 넣고,
  }
  
  private Object getBean(Class<?> type) {
    List<Object> objList = beans.get(type); // 객체 저장소에서 리스트(value)를 가져오고
    if(objList == null) { // 가져온게 없으면
      return null; // 없는 타입이니 null을 리턴
    }
    return objList.get(0); // 있으면 꺼낸걸 리턴
  }
  
  private int countBean(Class<?> type) {
    List<Object> objList = beans.get(type); // 객체 저장소에서 value를 가져오고,
    if(objList == null) { // 가져온게 null이면(값이 없으면)
      return 0; //size는 0이니 0을 리턴
    }
    return objList.size(); // 가져온게 있다면 그 갯수를 리턴한다.
  }
  
  
  class AutowiredMethod { 
    // BlackBox가 만들어지지 않아 Autowired가 실행되지 않은 객체를
    // 임시 보관하는 클래스
    Object object;
    Method method;
    
    public AutowiredMethod(Object object, Method method) {
      this.object = object;
      this.method = method;
    }
  }
  
}
