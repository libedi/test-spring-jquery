<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ajax �׽�Ʈ</title>
<script type="text/javascript" src="script/script.js"></script>
<script type="text/javascript" src="script/ajax_script.js"></script>
</head>
<body>
<form id="frm" name="frm" method="get" action="test.do?mode=ajax">
	ȸ�� ���<br/>
	ID : <input type="text" name="id"/>
	<input type="button" value="����" onclick="javascript:return startRequest();">
	<span id="ajaxok"></span>
</form>
</body>
</html>