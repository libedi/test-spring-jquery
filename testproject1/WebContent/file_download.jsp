<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>File 다운로드</title>
</head>
<body>
<form >
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>파일경로</th>
				<th>업로드날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="result">
				<tr>
					<td>${result.fileId }</td>
					<td>${result.filePath }</td>
					<td>${result.uploadDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
</body>
</html>