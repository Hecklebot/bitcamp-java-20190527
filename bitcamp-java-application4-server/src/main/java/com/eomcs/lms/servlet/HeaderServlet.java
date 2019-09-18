package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 역할:
// -> 화면 상단에 logger와 프로젝트 명을 출력한다.
// -> 로그인 정보를 출력한다.
// -> 로그인/로그아웃 버튼을 제공한다.

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 인클루딩 서블릿에는 setContentType()을 호출해도 소용없다.
    // 이 서블릿을 요청하는 쪽에서 처리하기 때문이다.
    // response.setContentLength("text/html;chatset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<div id='header'>");
    out.println("  <img src='/images/dance.gif'>");
    out.println("  <span>수업 관리 시스템</span>");
    out.println("  <a href='/auth/logout'>로그아웃</a>");
    out.println("  <a href='/auth/login'>로그인</a>");
    out.println("</div>");
  }
}
