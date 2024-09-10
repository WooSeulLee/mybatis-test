<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="getDate()">데이터 가져오기</button>
<table border ="1">
	<tr>
		<th>이름</th>
		<th>아이디</th>
		<th>생일</th>
	</tr>
	<tbody id="tbody"></tbody>
</table>



<script>
function getDate(){
	const xhr = new XMLHttpRequest(); //전화기 (readystate=0)
	
	xhr.open('GET','/json.jsp'); //전화번호 (readystate=1)
	xhr.onreadystatechange = function(){ // 받으면 뭘해야지~
		if(xhr.readyState==4){
			if(xhr.status==200){
				const users = JSON.parse(xhr.responseText);
				var html = '';
				for(var i=0 ; i<users.length ; i++){
					const user = users[i];
					html += '<tr>';
					html += '<td>' + user.uiName + '</td>'; 
					html += '<td>' + user.uiId + '</td>'; 
					html += '<td>' + user.uiBirth + '</td>'; 
					html += '</tr>';
				}
				document.querySelector('#tbody').innerHTML = html;
			}
		}
	}
	xhr.send();// 말해주는거
	
}
</script>

</body>
</html>