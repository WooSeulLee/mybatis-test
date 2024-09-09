<%@page import="com.mybatis.test.vo.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/user">
	<input type="text" name="uiNum" placeholder="유저번호">
	<input type="text" name="uiId" placeholder="ID">
	<input type="text" name="uiName" placeholder="이름">
	<button>검색</button>
</form>
<%
List<UserVO> users = (List<UserVO>) request.getAttribute("users");
%>
<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>생일</th>
		<th>취미</th>
		<th>가입일</th>
		<th>가입시간</th>
	</tr>
<%
for(UserVO user:users){
%>
	<tr>
		<td><%=user.getUiNum()%></td>
		<td><%=user.getUiId()%></td>
		<td><%=user.getUiName()%></td>
		<td><%=user.getUiBirth()%></td>
		<td><%=user.getUiHobby()%></td>
		<td><%=user.getCredat()%></td>
		<td><%=user.getCretim()%></td>
	</tr>
<%
}
%>
</table>
</body>
</html>