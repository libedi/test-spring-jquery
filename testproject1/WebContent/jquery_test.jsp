<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>jQuery �׽�Ʈ</title>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	alert("����� ���� : " + navigator.userAgent);
	alert("������ ����ũ�� : " + window.screen.availWidth);
	alert("������ ����ũ�� : " + window.screen.availHeight);
	var frm = document.frm;
	
	$("#btn").click(function(){
		alert("jQuery Ajax ���� �̺�Ʈ");

		$.ajax({
			type : "post",
			url : "test.do",
			data : "mode=json&id=" + $("input[name=id]").val(),
			dataType : "json",
		}).done(function(data){		// �ڵ����� eval() ����Ǿ��ִ�.
			alert(data.obj);
			alert(data.arr);
			
			$.each(data.arr, function(i, val){	// �迭 ������ ����Ʈ �Ǵ� ��ü�� iterator ���
				// �迭�� ��� index, value ����
				// ��ü�� ��� key, value ����
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
		<input type="button" id="btn" value="����"></input>
	</form>
</body>
</html>