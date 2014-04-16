// 로그인 폼 유효성 검사
function validateEncryptForm(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(!username || !password){
		alert("ID/비밀번호를 입력해주세요");
		return false;
	}
	
	try{
		var rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
		var rsaPublicKeyExponent = $("#rsaPublicKeyExponent").val();
		// submit
		submitEncryptForm(username, password, rsaPublicKeyModulus, rsaPublicKeyExponent);
	} catch(err){
		alert(err);
	}
	return false;
}

// RSA 암호화하여 정보전송
function submitEncryptForm(username, password, rsaPublicKeyModulus, rsaPublicKeyExponent){
	var rsa = new RSAKey();
	rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
	
	var securedUsername = hex2b64(rsa.encrypt(username));
	var securedPassword = hex2b64(rsa.encrypt(password));
	
	$("#securedUsername").val(securedUsername);
	$("#securedPassword").val(securedPassword);
	
	var securedForm = $("#securedForm");
	securedForm.submit();
}