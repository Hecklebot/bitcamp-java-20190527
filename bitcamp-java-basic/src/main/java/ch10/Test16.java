// 생성자 활용 - java.util.Calendar 클래스의 생성자
package ch10;

import java.util.Calendar;

public class Test16 {
  public static void main(String[] args) throws Exception {
    
    Calendar c1;
    // 생성자가 있다하더라도 접근 권한이 없으면 호출할 수 없다.
    //c1 = new Calendar(); // 컴파일 오류!
    
    // Calendar는 인스턴스 생성을 도와주는 별도의 클래스 메서드(스태틱 메서드)를 제공한다.
    c1 = Calendar.getInstance(); //getInstace 메소드(static)를 통해 객체를 생성
    
    System.out.println(c1.get(5)); //get 1=연, 2=월-1, 5=일, 이렇게 쓸 거면 주석달기, 그냥 이렇게 쓰지 말고
    System.out.println(c1.get(Calendar.DATE)); //이렇게 써라

//    final int YEAR = 2019; //상수는 어차피 고정값이니 굳이 인스턴스를 만들 때 마다 생성할 필요가 없다 -> static으로 만들어서 한 번만 생성, 관련 클래스에서 선언하고, 다른 클래스에서 쓸 수 있도록 public을 준다.
//    final int MONTH = 7;
//    final int DAY = 11;
    
    /* 
     # 팩토리 메서드(factory method)
     - GoF(Gang of Four)의 23가지 디자인 패턴(design pattern) 중 하나이다. 
     - 인스턴스를 생성해주는 메서드이다.
     - 인스턴스 생성 과정이 복잡할 경우에 인스턴스를 생성해주는 메서드를 미리 정의해 둔다.
     - 그래서 인스턴스가 필요할 때 마다 메서드를 호출하여 인스턴스를 리턴 받는다.
     
     # 싱글톤(singleton) 
     - GoF(Gang of Four)의 23가지 디자인 패턴(design pattern) 중 하나이다.
     - 인스턴스를 한 개만 생성하도록 제한할 때 사용한다.
     - 생성자를 private으로 처리하여 직접 인스턴스를 생성하지 못하도록 만든다.
     - 메서드를 통해 인스턴스를 생성하도록 유도한다.
     
     */
  }
}
















