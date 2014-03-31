<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� �׽�Ʈ</title>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var frm = document.boardFrm;
	
	$("#register").click(function(){
		location.href = "register.jsp";
	});
	
	$("#delete").click(function(){
		if('${fn:length(list)}' == 0){
			alert("����Ʈ�� �����ϴ�.");
		} else {
			$("input[name=check]:checked").each(function(index){
				alert("�ε��� : " + index);
			});
		}
	});
});
</script>
</head>
<body>
<form name="boardFrm">
	<div id="table">
	<c:choose>
		<c:when test="${fn:length(list) != 0 }">
			<table name="boardTable">
				<thead>
					<tr>
						<th>����</th>
						<th>��ȣ</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th>��ϳ�¥</th>
						<th>������¥</th>
						<th>��ȸ��</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="list">
						<tr>
							<td>
								<input type="checkbox" name="check" id="check"/>
							</td>
							<td>${list.id }</td>
							<td>
								<a href="board.do?mode=getBoardInfo&id=${list.id }">
									${list.title }
								</a>
							</td>
							<td>${list.writer }</td>
							<td>${list.createDate }</td>
							<td>${list.modifyDate }</td>
							<td>${list.clickView }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			����Ʈ�� �����ϴ�.
		</c:otherwise>
	</c:choose>
	</div>

	<div id="input">
	<input type="button" name="register" id="register" value="���"/>
	<input type="button" name="delete" id="delete" value="����"/>
	</div>
</form>
</body>
</html>