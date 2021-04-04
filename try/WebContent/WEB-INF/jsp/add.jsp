<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録</title>
</head>
<body>
	<c:forEach var="error" items="${errors}">
		<c:out value="${error}" /><br>
	</c:forEach>
	<form action="/try/useradd" method="POST">
		<label for="name">名前</label>
		<input type="text" id="name" name="name"/>
		<br>

		<label for="team_name">チーム名</label>
		<select id="team_name" name="team_id">
			<c:forEach var="team" items="${teamList}">
				<option value="${team.teamId}"><c:out value="${team.name}"/></option>
				<c:if test="${subject =='JAVA'}">checked</c:if>
			</c:forEach>
		</select>
		<br>

		<input type="submit" value="登録">
	</form>
</body>
</html>