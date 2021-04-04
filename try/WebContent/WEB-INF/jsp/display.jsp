<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX2</title>
</head>
<body>
	<table>
		<tr>
			<th>名前</th>
			<td><c:out value="${name}"/></td>
		</tr>
		<tr>
			<th>誕生日</th>
			<td>
				<fmt:formatDate value="${birthday}" pattern="yyyy/MM/dd" var="formattedDate"/>
				<c:out value="${formattedDate}"/>
			</td>
		</tr>
		<tr>
			<th>性別</th>
			<td><c:out value="${gender}"/></td>
		</tr>
		<tr>
			<th>血液型</th>
			<td><c:out value="${bloodtype}"/></td>
		</tr>
		<tr>
			<th>学習内容</th>
			<td>
				<c:forEach var="subject" items="${subjects}">
					<c:out value="${subject}"/>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>備考</th>
			<td><pre><c:out value="${note}"/></pre></td>
		</tr>
	</table>
</body>
</html>