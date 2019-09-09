package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component
public class MemberCommand {

  //  private static final Logger logger = LogManager.getLogger(PhotoBoardCommand.class);

  private MemberDao memberDao;

  public MemberCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/member/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록폼</title></head>");
    out.println("<body><h1>회원 등록폼</h1>");
    out.println("<form action='/member/add'>");
    out.println("이름 : <input type='text' name='name'></textarea><br/>");
    out.println("이메일 : <input type='text' name='email'></textarea><br/>");
    out.println("암호 : <input type='text' name='password'></textarea><br/>");
    out.println("사진 : <input type='text' name='photo'></textarea><br/>");
    out.println("전화 : <input type='text' name='tel'></textarea><br/>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/member/add")
  public void add(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'"
        + "</head>");
    out.println("<body><h1>회원 등록</h1>");

    try {
      Member member = new Member();
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));
      System.out.println(member);
      memberDao.insert(member);
      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @RequestMapping("/member/delete")
  public void delete(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원정보 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'"
        + "</head>");
    out.println("<body><h1>회원정보 삭제</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if (memberDao.delete(no) > 0) {
        out.println("<p>데이터를 삭제하였습니다.</p>");
      } else {
        out.println("<p>해당 데이터가 없습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<p>데이터 삭제에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/member/detail")
  public void detail(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 상세</title></head>");
    out.println("<body><h1>회원 상세</h1>");
    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findBy(no);

      if (member == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
      } else {
        out.println("<form action='/member/update'>");
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
    out.println("</body></html>");
  }

  @RequestMapping("/member/list")
  public void list(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 목록</title>"
        + "<link rel=\'stylesheet\' href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' "
        + "integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "</head>");
    out.println("<body><h1>회원 목록</h1>");
    out.println("<a href='/member/form'>새 회원</a>");
    
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
      out.println("<form action='http://localhost:8888/auth/login'>");
      out.println("이메일 <input type='text' name='email'>");
      out.println("비밀번호 <input type='text' name='password'>  ");
      out.println("<button>로그인</button></br>");
      out.println("</form>");

      out.println("<form action='http://localhost:8888/member/search'>");
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

  @RequestMapping("/member/update")
  public void update(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원정보 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'"
        + "</head>");
    out.println("<body><h1>회원정보 변경</h1>");

    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));

      memberDao.update(member);
      out.println("<p>변경 완료</p>");
    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");

  }

  @RequestMapping("/member/search")
  public void search(ServletRequest request, ServletResponse response) throws IOException {
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
          out.println("<form action='/member/update'>");
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
