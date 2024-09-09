<%@page import="com.mybatis.test.vo.CarVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
CarVO car = (CarVO)request.getAttribute("car");
%>
<form method="POST" action="/car/car-update" id="frm">
	<input type="hidden" name="ciNum" value="<%=car.getCiNum()%>">
	<table border="1">
		<tr>
			<th>모델번호</th>
			<td><%=car.getCiNum() %></td>
		</tr>
		<tr>
			<th>모델명</th>
			<td><input type="text" name="ciName" value="<%=car.getCiName()%>"></td>
		</tr>
		<tr>
			<th>년식</th>
			<td><input type="text" name="ciYear" value="<%=car.getCiYear()%>"></td>
		</tr>
		<tr>
			<th colspan="2"><button>수정</button> <button type="button" onclick="changeAction(this.form)">삭제</button></th>
		</tr>
	</table>
</form>
<script>
function changeAction(frm){
	frm.action='/car/car-delete';
	frm.submit();
}
</script>
</body>
</html>