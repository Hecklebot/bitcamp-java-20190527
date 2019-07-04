//애플리케이션 메인 클래스
//애플리케이션을 실행할 때 이 클래스를 실행한다.
//어케바꾸냐 바꿔줘

package com.eomcs.lms;

public class AppTest {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        System.out.println("this code written by labtop");
        System.out.println("this is test code");
    }
}
