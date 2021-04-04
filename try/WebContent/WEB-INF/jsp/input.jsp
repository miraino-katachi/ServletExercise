<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX1</title>
</head>
<body>
	<c:forEach var="error" items="${errors}">
		<c:out value="${error}" /><br>
	</c:forEach>
	<form action="/try/ex2" method="post">
		<label>名前：</label>
		<input type="text" name="name" value="${name}">
		<br>

		<label>誕生日：</label>
		<fmt:formatDate value="${birthday}" pattern="yyyy-MM-dd" var="formattedDate"/>
		<input type="date" name="birthday" value="${formattedDate}">
		<br>

		<label>性別</label>
		<input type="radio" name="gender" value="男性"   <c:if test="${gender == '男性'}"  >checked</c:if> />男性
		<input type="radio" name="gender" value="女性"   <c:if test="${gender == '女性'}"  >checked</c:if> />女性
		<input type="radio" name="gender" value="その他" <c:if test="${gender == 'その他'}">checked</c:if> />その他
		<br>

		<label>血液型</label>
		<select name="bloodtype">
			<option value="A型"  <c:if test="${bloodtype == 'A型'}" >selected</c:if>>A型</option>
			<option value="B型"  <c:if test="${bloodtype == 'B型'}" >selected</c:if>>B型</option>
			<option value="O型"  <c:if test="${bloodtype == 'O型'}" >selected</c:if>>O型</option>
			<option value="AB型" <c:if test="${bloodtype == 'AB型'}">selected</c:if>>AB型</option>
			<option value="不明" <c:if test="${bloodtype == '不明'}">selected</c:if>>不明</option>
		</select>
		<br>

		<label>学習内容</label>
		<input type="checkbox" name="subjects" value="JAVA"
			<c:forEach var="subject" items="${subjects}">
				<c:if test="${subject =='JAVA'}">checked</c:if>
			</c:forEach>
		/>JAVA
		<input type="checkbox" name="subjects" value="PHP"
			<c:forEach var="subject" items="${subjects}">
				<c:if test="${subject =='PHP'}">checked</c:if>
			</c:forEach>
		/>PHP
		<input type="checkbox" name="subjects" value="HTML/CSS"
			<c:forEach var="subject" items="${subjects}">
				<c:if test="${subject =='HTML/CSS'}">checked</c:if>
			</c:forEach>
		/>HTML/CSS
		<input type="checkbox" name="subjects" value="デザイン"
			<c:forEach var="subject" items="${subjects}">
				<c:if test="${subject =='デザイン'}">checked</c:if>
			</c:forEach>
		/>デザイン
		<input type="checkbox" name="subjects" value="その他"
			<c:forEach var="subject" items="${subjects}">
				<c:if test="${subject =='その他'}">checked</c:if>
			</c:forEach>
		/>その他
		<br>

		<label>備考</label>
		<textarea name="note"><c:out value="${note}"></c:out></textarea>
		<br>

		<input type="submit" value="送信">
	</form>
</body>
</html>