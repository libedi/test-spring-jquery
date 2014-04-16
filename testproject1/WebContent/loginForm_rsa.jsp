<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 폼 - RSA 암호화 알고리즘 사용</title>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="script/encrypt/rsa/jsbn.js"></script>
<script type="text/javascript" src="script/encrypt/rsa/rsa.js"></script>
<script type="text/javascript" src="script/encrypt/rsa/prng4.js"></script>
<script type="text/javascript" src="script/encrypt/rsa/rng.js"></script>
<script type="text/javascript" src="script/encrypt/rsa/base64.js"></script>
<script type="text/javascript" src="script/login.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#loginBtn").click(function(){
		validateEncryptForm();
	});
});
</script>
</head>
<body>
<input type="hidden" name="rsaPublicKeyModulus" id="rsaPublicKeyModulus" value="${publicKeyModulus }"/>
<input type="hidden" name="rsaPublicKeyExponent" id="rsaPublicKeyExponent" value="${publicKeyExponent }" />

<form id="securedForm" action="test.do?mode=login" method="post">
	<label for="username">ID:<input type="text" name="username" id="username" /></label>
	<label for="password">PW:<input type="password" name="password" id="password" /></label>
	<input type="button" id="loginBtn" value="로그인"/>
	<!-- encrypted data -->	
	<input type="hidden" name="securedUsername" id="securedUsername" />
	<input type="hidden" name="securedPassword" id="securedPassword" />
</form>
</body>
</html>