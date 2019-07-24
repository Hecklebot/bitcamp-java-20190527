package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class HiCommand implements Command{
  
  private Input input;
  
  public HiCommand (Input input) {
    this.input = input;
  }
  
  @Override
  public void execute() {
    System.out.printf("안녕하세요, %s님!\n", input.getStringValue("이름? "));
  }
}
