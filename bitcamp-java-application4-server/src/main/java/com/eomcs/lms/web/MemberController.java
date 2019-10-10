package com.eomcs.lms.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Resource
  private MemberService memberService;
  private String uploadDir;

  public MemberController(ServletContext sc) {
    uploadDir = sc.getRealPath("/upload/member");
  }

  @RequestMapping("form")
  public void form(HttpServletRequest request) throws Exception {}

  @RequestMapping("list")
  public void list(Model model) throws Exception {

    List<Member> members = memberService.list();

    model.addAttribute("members", members);
  }

  @PostMapping("add")
  public String add(Member member, MultipartFile file) throws Exception {

    member.setPhoto(writeFile(file));
    memberService.insert(member);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(int no) throws Exception {
    memberService.delete(no);
    return "redirect:list";
  }

  @RequestMapping("detail")
  public void detail(Model model, int no) throws Exception {

    Member member = memberService.get(no);
    model.addAttribute("member", member);
  }

  @RequestMapping("search")
  public void search(String keyword, Model model) throws Exception {
    List<Member> members = memberService.search(keyword);
    model.addAttribute("members", members);
  }

  @PostMapping("update")
  public String update(Member member, MultipartFile file) throws Exception {

    member.setPhoto(writeFile(file));
    memberService.update(member);
    return "redirect:list";
  }

  public Member login( String email, String password) throws Exception {
      Map<String,Object> params = new HashMap<>();
      params.put("email", email);
      params.put("password", password);

      return memberService.login(params);
  }


  private String writeFile(MultipartFile file) throws IllegalStateException, IOException {
    if (file.isEmpty()) {
      return null;
    }
    String fileName = UUID.randomUUID().toString();
    file.transferTo(new File(uploadDir + "/" + fileName));
    return fileName;
  }



}
