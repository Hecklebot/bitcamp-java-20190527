package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/detail")
public class MemberDetailCommand extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 상세</title>"
        + "<link rel=\'stylesheet\' href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\'integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "<link rel='stylesheet' href='/css/common.css'>"
        + "</head>");
    out.println("<body>");
    request.getRequestDispatcher("/header").include(request, response);
    out.println("<div id='content'>");
    out.println("<h1>회원 상세</h1>");
    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findBy(no);

      if (member == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
      } else {
        out.println("<form action='/member/update' method='post'>");
        out.printf("번호: <input type='text' name='no' value='%d' readonly><br/>",  member.getNo());
        out.printf("이름: <textarea name='name' rows='1' cols='50'>%s</textarea><br/>\n",
            member.getName());
        out.printf("이메일: <textarea name='email' rows='1' cols='50'>%s</textarea><br/>\n", 
            member.getEmail());
        out.printf("사진: <textarea name='photo' rows='1' cols='50'>%s</textarea><br/>\n", 
            member.getPhoto());
        out.printf("전화번호: <textarea name='tel' rows='1' cols='50'>%s</textarea><br/>\n", 
            member.getTel());
        out.printf("등록일: <textarea name='registeredDate' rows='1' cols='50'>%s</textarea><br/>\n", 
            member.getRegisteredDate());
        out.println("<button>변경</button>");
        out.printf("<button><a href='/member/delete?no=%d'>삭제</a></button>\n", 
            member.getNo());
        out.println("</form>");
      }
    } catch (Exception e) {
      System.out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</div>");
    request.getRequestDispatcher("/footer").include(request, response);
    out.println("</body></html>");
  }
}
