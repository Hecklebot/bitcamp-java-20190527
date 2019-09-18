package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 역할:
// -> 로그인 사용자만 등록, 변경, 삭제를 수행할 수 있게한다.
@WebFilter({"*add", "*update, *delete"})
public class AuthFilter implements Filter{
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
  }
  
}
