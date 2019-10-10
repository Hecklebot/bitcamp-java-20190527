package com.eomcs.lms.web.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@RestController("json.MemberController")
@RequestMapping("/json/member")
public class MemberController {

  @Resource 
  private MemberService memberService;
  String uploadDir;

  public MemberController(ServletContext sc) {
    this.uploadDir = sc.getRealPath("upload/member");
  }


  @RequestMapping("list")
  public JsonResult list() {
    try {
      List<Member> members = memberService.list();
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(members);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @PostMapping("add")
  public JsonResult add(Member member, MultipartFile file) throws Exception {
    try {
      member.setPhoto(writeFile(file));
      memberService.insert(member);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("delete")
  public JsonResult delete(int no) throws Exception {
    memberService.delete(no);
    return new JsonResult().setState(JsonResult.SUCCESS);
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) throws Exception {
    Member member = memberService.get(no);
    return new JsonResult().setState(JsonResult.SUCCESS).setResult(member);
  }

  @RequestMapping("search")
  public JsonResult search(String keyword) {

    try {
      List<Member> members = memberService.search(keyword);
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(members);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @PostMapping("update")
  public JsonResult update(Member member, MultipartFile file) {
    try {
      member.setPhoto(writeFile(file));
      memberService.update(member);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @PostMapping("login")
  public JsonResult login(Member member, String email, String password) {
    try {
      Map<String,Object> params = new HashMap<>();
      params.put("email", email);
      params.put("password", password);

      member = memberService.login(params);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch(Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  private String writeFile(MultipartFile file) throws IllegalStateException, IOException {
    if (!file.isEmpty()) {
      return null;
    }
    String fileName = UUID.randomUUID().toString();
    file.transferTo(new File(uploadDir + "/" + fileName));
    return fileName;
  }

}
