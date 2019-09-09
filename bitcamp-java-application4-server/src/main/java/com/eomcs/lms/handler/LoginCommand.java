package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component
public class LoginCommand {
  
//  private static final Logger logger = LogManager.getLogger(PhotoBoardCommand.class);
  
  private MemberDao memberDao;
  
  public LoginCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/auth/login")
  public void login(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>로그인</title>"
        + "<meta http-equiv='Refresh' content='1';url=/member/list'" 
        + "</head>");
    out.println("<body><h1>로그인</h1>");

    try {
      HashMap<String,Object> params = new HashMap<>();
      params.put("email", request.getParameter("email"));
      params.put("password", request.getParameter("password"));
      
      Member member = memberDao.findByEmailPassword(params);
      if(member == null) {
        out.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
      } else {
        out.printf("<p>%s 님 환영합니다.\n</p>", member.getName());
      }

    } catch (Exception e) {
      out.println("<p>로그인에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
  }

}
