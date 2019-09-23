<%@page import="com.eomcs.lms.dao.MemberDao"%>
<%@page import="com.eomcs.lms.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <title>회원 검색</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
</head>
<body>
  <jsp:include page="../header.jsp"/>
  <h1>회원 검색</h1>
     <table class='table table-hover'>
     <tr><th>번호</th><th>이름</th><th>이메일</th><th>전화</th><th>등록일</th></tr>
      
      <% 
        List<Member> members;
        MemberDao memberDao;
        members = memberDao.findByKeyword(request.getParameter("keyword"));
        for (Member member : members) {
          pageContext.setAttribute("member", member);
      %>
        <tr>
          <td>${member.no}</td>
          <td><a href='/member/detail?no=${member.no}'>${member.name}</a></td>
          <td>${member.email}</td>
          <td>${member.tel}</td>
          <td>${member.registeredDate}</td>
        </tr> 
      <%}%>
     </table>
  <jsp:include page="../footer.jsp"/>
</body>
</html>
     