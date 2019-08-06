package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {

  int insert(Lesson lesson) throws Exception;
  
  List<Lesson> findAll() throws Exception;
  
  Lesson findBy(int no) throws Exception;
  
  int update(Lesson lesson) throws Exception;
  
  int delete(int no) throws Exception;
  
}
