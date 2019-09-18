package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/search")
public class MemberSearchCommand extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private MemberDao memberDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 검색</title></head>");
    out.println("<body><h1>회원 검색</h1>");

    try {
      String name = request.getParameter("name");
      List<Member> list = memberDao.findByKeyword(name);

      for (Member member : list) {
        if(member == null) {
          out.println("일치하는 회원이 없습니다.");
        } else {
          out.println("<form action='/member/update' method='post'>");
          out.printf("번호: <input type='text' name='no' value='%d' readonly><br/>\n",  
              member.getNo());
          out.printf("이름: <input type='text' name='name' value='%s' readonly><br/>\n",
              member.getName());
          out.printf("이메일: <input type='text' name='email' value='%s' readonly><br/>\n", 
              member.getEmail());
          out.printf("사진: <input type='text' name='photo' value='%s' readonly><br/>\n", 
              member.getPhoto());
          out.printf("전화번호: <input type='text' name='tel' value='%s' readonly><br/>\n", 
              member.getTel());
          out.printf("등록일: <input type='text' name='registeredDate' value='%s' readonly><br/><br/>\n", 
              member.getRegisteredDate());
        }
      }

    } catch(Exception e) {
      out.println("데이터 검색에 실패했습니다!");
      System.out.println(e.getMessage());
    } finally {
      out.println("</body></html>");
    }
  }
}
