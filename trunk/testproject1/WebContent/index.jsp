<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Main Page</title>
<script type="text/javascript">
// ����� �������� �̵� (-user-agent ��� ���)
/*
var mobileKeyWords = new Array('iphone', 'ipod', 'blackberry', 'android');

for(var word in mobileKeyWords){
	if(navigator.userAgent.toLowerCase().match(mobileKeyWords[word]) != null){
		window.location.href = "m_index.jsp";
		break;
	}
}
*/
// ����� �������� �̵� (platform ���� �Ǵ�)
alert(navigator.platform);
var filter = "win16|win32|win64|mac|macintel";
if( navigator.platform  ){
    if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
    	window.location.href = "m_index.jsp";
    }
}
</script>
</head>
<body>
	<!--jsp:forward page="cust.do?mode=register"/-->
	
	<a href="ajax_test.jsp">Ajax �׽�Ʈ</a>
	<a href="json_test.jsp">JSON �׽�Ʈ</a>
	<a href="jquery_test.jsp">JQuery �׽�Ʈ</a>
	<a href="file_upload.jsp">File���ε� �׽�Ʈ</a>
	<a href="test.do?mode=getFileList&page=1">File �ٿ�ε� �׽�Ʈ</a>
	
</body>
</html>