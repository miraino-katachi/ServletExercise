<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX8</title>
</head>
<body>
	<table>
		<tr>
			<th>名前</th>
			<th>チーム名</th>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td><c:out value="${user.name}"></c:out></td>
				<td><c:out value="${user.teamName}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>