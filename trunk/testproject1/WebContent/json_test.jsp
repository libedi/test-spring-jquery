<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="script/script.js"></script>
<script type="text/javascript" src="script/json_script.js"></script>
</head>
<body>
<form name="frm">
	ID : <input type="text" name="id"/>
	<input type="button" value="전송" onclick="javascript:return jsonTest();"></input>
	<div id="jsonOk"></div>
</form>
</body>
</html>