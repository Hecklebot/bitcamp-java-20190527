<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
  <title>회원 등록폼</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
  <link rel='stylesheet' href='/css/common.css'>
</head>
<body>
  <jsp:include page="../header.jsp"/>
  <div id='content'>
    <h1>회원 등록폼</h1>
    <form action='add' method='post' enctype='multipart/form-data'>
      이름: <input type='text' name='name'><br>
      이메일: <input type='text' name='email'><br>
      암호: <input type='text' name='password'><br>
      사진: <input type='file' name='file'><br>
      전화: <input type='text' name='tel'><br>
    <button>등록</button>
    </form>
  </div>
  <jsp:include page="../footer.jsp"/>
</body>
</html>
