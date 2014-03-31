<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 등록 페이지</title>
</head>
<body>
<form action="board.do?mode=boardRegister" method="post">
	<fieldset>
		<legend>등록</legend>
		<label for="writer">이름</label>
		<input type="text" name="writer" id="writer"/><br/>
		<label for="title">제목</label>
		<input type="text" name="title" id="title"/><br/>
		<label for="text">내용</label>
		<textarea rows="5" cols="10" name="text" id="text"></textarea><br/>
	</fieldset>
	<input type="submit" value="등록"/>
</form>
</body>
</html>