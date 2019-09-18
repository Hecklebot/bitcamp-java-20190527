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

@WebServlet("/member/list")
public class MemberListCommand extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private MemberDao memberDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }
  
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 목록</title>"
        + "<link rel=\'stylesheet\' href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' "
        + "integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "</head>");
    out.println("<body><h1>회원 목록</h1>");
    out.println("<a href='/member/add'>새 회원</a>");
    
    try {
      out.println("<table class='table table-hover'>");
      out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>사진</th><th>전화번호</th>"
          + "<th>등록일</th>");
      List<Member> members = memberDao.findAll();
      for (Member member : members) {
        out.printf("<tr><td>%d</td>"
            + "<td><a href='/member/detail?no=%d'>%s</a></td><td>%s</td>"
            + "<td>%s</td><td>%s</td><td>%s</td></tr>\n", 
            member.getNo(), member.getNo(), member.getName(), 
            member.getEmail(), member.getPhoto(), member.getTel(),
            member.getRegisteredDate());
      }
      out.println("</table>");
      out.println("<form action='/auth/login' method='post'>");
      out.println("이메일 <input type='text' name='email'>");
      out.println("비밀번호 <input type='text' name='password'>  ");
      out.println("<button>로그인</button></br>");
      out.println("</form>");

      out.println("<form action='/member/search' method='post'>");
      out.println("<input type='text' name='name'>");
      out.println("<button>검색</button><br/>");
      out.println("</form>");

      out.println("<a href='/lesson/list'>수업 목록</a>");
      out.println("<a href='/board/list'>게시판</a>");
      out.println("<a href='/photoboard/list'>사진게시판</a>");


    } catch (Exception e) {
      out.println("<p>데이터 목록 조회에 실패했습니다!</p>");
      throw new RuntimeException(e);
    } finally {
      out.println("</body></html>");
    }
  }
}
