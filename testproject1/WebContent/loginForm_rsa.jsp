<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
</head>
<body>
<input type="hidden" name="rsaPublicKeyModulus" id="rsaPublicKeyModulus" value="${publicKeyModulus }"/>
<input type="hidden" name="rsaPublicKeyExponent" id="rsaPublicKeyExponene" value="${publicKeyExponent }" />
<div id="loginDiv">
	<label for="username"><input type="text" name="username" id="username" /></label>
	<label for="password"><input type="text" name="password" id="password" /></label>
</div>
<form id="securedForm" action="test.do?mode=login">
	<input type="hidden" name="securedUsername" id="securedUserName" />
	<input type="hidden" name="securedPassword" id="securedPassword" />
</form>
</body>
</html>