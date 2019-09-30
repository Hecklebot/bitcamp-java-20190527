package com.eomcs.lms.web;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Resource 
  private MemberDao memberDao;

  @RequestMapping("form")
  public void form(HttpServletRequest request) 
      throws Exception {
  }

  @RequestMapping("list")
  public void list(Model model) 
      throws Exception {
  
    List<Member> members = memberDao.findAll();
  
    model.addAttribute("members", members);
  }

  @RequestMapping("add")
  public String add(Member member, Part file, HttpServletRequest request) 
      throws Exception {
    String uploadDir = request.getServletContext().getRealPath("/upload/member");
    
    if (file != null && file.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      file.write(uploadDir + "/" + filename);
    }
    
    memberDao.insert(member);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(int no) 
      throws Exception {
  
    if (memberDao.delete(no) == 0) {
      throw new Exception("해당 데이터가 없습니다.");
    }
    return "redirect:list";
  }

  @RequestMapping("detail")
  public void detail(Model model, int no) 
      throws Exception {
  
    Member member = memberDao.findBy(no);
    if (member == null) {
      throw new Exception("해당 번호의 데이터가 없습니다!");
    } 
  
    model.addAttribute("member", member);
  }

  @RequestMapping("search")
  public void search(String keyword, Model model) 
      throws Exception {
  
    List<Member> members = memberDao.findByKeyword(keyword);
  
    model.addAttribute("members", members);
  }

  @RequestMapping("update")
  public void update(Member member, HttpServletRequest request, Part file) 
      throws Exception {
    String uploadDir = request.getServletContext().getRealPath("/upload/member");
  
    // 업로드 된 사진 파일 처리
    if (file != null && file.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      file.write(uploadDir + "/" + filename);
    }
  
    memberDao.update(member);
  }
}
