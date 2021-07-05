<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>springboot 메인화면</h1>
	<form action="${ pageContext.request.contextPath }/selectOneMember" method="get">
		<input type="text" name="userId" placeholder="회원아이디입력">
		<input type="submit" value="찾기">
	</form>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>이메일</th>
			<th>전화번호 </th>
			<th>아이디</th>
		</tr>
		<c:forEach var="m" items="${ list }">
			<tr>
				<td>${ m.userId }</td>
				<td>${ m.userName }</td>
				<td>${ m.age }</td>
				<td>${ m.gender }</td>
				<td>${ m.email }</td>
				<td>${ m.phone }</td>
				<td>${ m.address }</td>
			</tr>
		</c:forEach>
	</table>
	${list }
</body>
</html>