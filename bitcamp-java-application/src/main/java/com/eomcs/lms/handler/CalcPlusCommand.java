package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalcPlusCommand implements Command {

  private Input input;

  public CalcPlusCommand(Input input) {
    this.input = input;
  }

  @Override
  public void execute() {
    System.out.println("합계는 " + (input.getIntValue("값 1? ") + input.getIntValue("값 2? ")) + "입니다.");
  }

}
