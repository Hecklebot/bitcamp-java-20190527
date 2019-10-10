package com.eomcs.lms.web;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {

  @Resource
  private LessonService lessonService;

  @RequestMapping("form")
  public void form() {
  }

  @RequestMapping("list")
  public void list(Model model) throws Exception {
    List<Lesson> lessons = lessonService.list();
    model.addAttribute("lessons", lessons);
  }

  @RequestMapping("add")
  public String add(Lesson lesson) throws Exception {
    lessonService.insert(lesson);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(int no) throws Exception {
    lessonService.delete(no);
    return "redirect:list";
  }

  @RequestMapping("detail")
  public void detail(Model model, int no) throws Exception {
    Lesson lesson = lessonService.get(no);
    model.addAttribute("lesson", lesson);
  }

  @RequestMapping("update")
  public String update(Lesson lesson) throws Exception {
    lessonService.update(lesson);
    return "redirect:list";
  }
}