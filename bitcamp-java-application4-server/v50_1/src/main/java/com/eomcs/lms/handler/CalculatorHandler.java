package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import org.springframework.stereotype.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component
public class CalculatorHandler {
  
  @RequestMapping("/calc/plus")
  public void plus(BufferedReader in, PrintStream out) {
    try {
      int a = Input.getIntValue(in, out, "값1? ");
      int b = Input.getIntValue(in, out, "값2? ");
      out.printf("%d + %d = %d\n", a,b,(a+b));
      out.flush();
    } catch(Exception e) {
      out.println("입력 값이 옳지 않습니다.");
      e.printStackTrace();
    }
  }
  
  
  @RequestMapping("/calc/minus")
  public void minus(BufferedReader in, PrintStream out) {
    try {
      int a = Input.getIntValue(in, out, "값1? ");
      int b = Input.getIntValue(in, out, "값2? ");
      out.printf("%d - %d = %d\n", a,b,(a-b));
      out.flush();
    } catch(Exception e) {
      out.println("입력 값이 옳지 않습니다.");
      e.printStackTrace();
    }
  }
  
  
}

// 실습 과제
// -> 다음과 같이 실행하도록 위 클래스를 완성하시오.
// 
/*
> /calc/plus
값1? 100
값2? 200
100 + 200 = 300

> /calc/minus
값1? 100
값2? 200
100 - 200 = -100
*/
