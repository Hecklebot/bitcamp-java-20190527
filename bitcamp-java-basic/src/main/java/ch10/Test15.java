// 생성자 활용 - java.util.Date 클래스의 생성자
package ch10;

import java.util.Date;

public class Test15 {
  public static void main(String[] args) throws Exception {
    
    // Date() 기본 생성자
    Date d1 = new Date(); // 현재 시간을 저장한다.
    System.out.println(d1);
    
    // Date(long) : 1970-01-01 00:00:00 부터 지금까지 경과된 밀리초 
    Date d2 = new Date(1000);
    System.out.println(d2);
    
    Date d3 = new Date(System.currentTimeMillis());
    System.out.println(d3);
    
    Date d4 = new Date(119, 0, 15); //year은 1900년을 기준으로 더함. 1900 + 119 = 2019. 걍 쓰지마라
    System.out.println(d4);
    
    // java.sql.Date
    java.sql.Date d5 = new java.sql.Date(System.currentTimeMillis()); //sql의 Date는 util의 Date를 상속받은 subClass
    System.out.println(d5);
    
    // 간접적으로 객체를 생성하기
    java.sql.Date d6 = java.sql.Date.valueOf("2019-1-16"); //스태틱 메소드를 사용해 객체생성
    System.out.println(d6);
  }
}
















