<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Main Page</title>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="script/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$("#ajaxupload").click(function(){
		
		// jquery.form.js의 ajaxForm 설정
		$("form[name=frm]").ajaxForm({
			beforeSend: function(){
				alert("ajax upload");
			},
			uploadProgress: function(event, position, total, percentComplete){
				alert("uploading....");
			},
			complete: function(xhr){
				alert("complete!");
			}
		});
		
		// ajaxForm 이벤트 호출
		$("form[name=frm]").submit();
	});
	
});
</script>
</head>
<body>
<form name="frm" action="test.do?mode=fileUpload" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>파일 업로드</legend>
		<input type="file" id="upload" name="upload"></input>
		<input type="submit" value="UPLOAD"/>
		<input type="button" id="ajaxupload" value="AJAX UPLOAD"/>
	</fieldset>
</form>
</body>
</html>