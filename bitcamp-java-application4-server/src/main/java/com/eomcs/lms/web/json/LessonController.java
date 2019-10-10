package com.eomcs.lms.web.json;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@RestController("json.LessonController")
@RequestMapping("/json/lesson")
public class LessonController {

  @Resource
  private LessonService lessonService;

  @RequestMapping("list")
  public JsonResult list() {

    try {
      List<Lesson> lessons = lessonService.list();
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(lessons);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("add")
  public JsonResult add(Lesson lesson) {
    try {
      lessonService.insert(lesson);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("delete")
  public JsonResult delete(int no) throws Exception {
    lessonService.delete(no);
    return new JsonResult().setState(JsonResult.SUCCESS);
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) throws Exception {
    Lesson lesson = lessonService.get(no);
    return new JsonResult().setState(JsonResult.SUCCESS).setResult(lesson);
  }

  @RequestMapping("update")
  public JsonResult update(Lesson lesson) {
    try {
      lessonService.update(lesson);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }
}