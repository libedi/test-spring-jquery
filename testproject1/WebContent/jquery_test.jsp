<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>jQuery 테스트</title>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	alert("모바일 정보 : " + navigator.userAgent);
	alert("브라우저 가로크기 : " + window.screen.availWidth);
	alert("브라우저 세로크기 : " + window.screen.availHeight);
	var frm = document.frm;
	
	$("#btn").click(function(){
		alert("jQuery Ajax 구현 이벤트");

		$.ajax({
			type : "post",
			url : "test.do",
			data : "mode=json&id=" + $("input[name=id]").val(),
			dataType : "json",
		}).done(function(data){		// 자동으로 eval() 적용되어있다.
			alert(data.obj);
			alert(data.arr);
			
			$.each(data.arr, function(i, val){	// 배열 형태인 리스트 또는 객체의 iterator 기능
				// 배열인 경우 index, value 구조
				// 객체인 경우 key, value 구조
				alert("index : " + i + " - value : " + val);
			});
		});
	});
});
</script>
</head>
<body>
	<form name="frm" id="frm">
		<input type="text" name="id"></input>
		<input type="button" id="btn" value="전송"></input>
	</form>
</body>
</html>