package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.util.Input;

public class LessonDeleteCommand implements Command {
  
  private LessonDao lessonDao;
  private Input input;
  
  public LessonDeleteCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");
    
    try {
      lessonDao.delete(no);
      if(lessonDao.findBy(no) == null) {
        System.out.println("해당 번호의 데이터가 없습니다!");
      }
    } catch(Exception e) {
      System.out.println("데이터 삭제 중 오류가 발생했습니다");
      System.out.println(e.getMessage());
    }
    
  }
  
  
  
}












