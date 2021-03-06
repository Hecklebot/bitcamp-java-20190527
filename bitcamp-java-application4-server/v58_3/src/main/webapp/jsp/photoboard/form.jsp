<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
  <title>사진게시물 등록폼</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
  <link rel='stylesheet' href='/css/common.css'>
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div id='content'>
    <h1>사진게시물 등록폼</h1>
    <form action='add' method='post' enctype='multipart/form-data'>
      제목: <input type='text' name='title'><br>
      수업: <input type='text' name='lessonNo'><br>
      사진: <input type='file' name='filePath'><br>
      사진: <input type='file' name='filePath'><br>
      사진: <input type='file' name='filePath'><br>
      사진: <input type='file' name='filePath'><br>
      사진: <input type='file' name='filePath'><br>
      사진: <input type='file' name='filePath'><br>
      <button>등록</button>
    </form>
  </div>
  <jsp:include page="../footer.jsp"/>
</body>
</html>
