package com.eomcs.lms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

// LessonService 구현체
@Service
public class DefaultLessonService implements LessonService {

  @Resource
  private LessonDao lessonDao;

  @Override
  public void insert(Lesson Lesson) throws Exception {
    lessonDao.insert(Lesson);
  }

  @Override
  public void delete(int no) throws Exception {
    if (lessonDao.delete(no) == 0) {
      throw new Exception("해당 데이터가 없습니다.");
    }
  }

  @Override
  public Lesson get(int no) throws Exception {
    Lesson lesson = lessonDao.findBy(no);
    if (lesson == null) {
      throw new Exception("해당 번호의 데이터가 없습니다!");
    } 
    return lesson;
  }

  @Override
  public List<Lesson> list() throws Exception {
    List<Lesson> lesson = lessonDao.findAll();
    return lesson;
  }

  @Override
  public void update(Lesson Lesson) throws Exception {
    lessonDao.update(Lesson);
  }
}
