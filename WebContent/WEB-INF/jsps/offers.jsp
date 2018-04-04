<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/static/css/offers.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table class="offersTable">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Text</th>

		</tr>
		<c:forEach var="row" items="${offers}">
			<tr>
				<td><c:out value="${row.id}"></c:out></td>
				<td><c:out value="name: ${row.name}"></c:out></td>

				<td><c:out value="email: ${row.email}"></c:out></td>

				<td><c:out value="text: ${row.text}"></c:out>
			</tr>

		</c:forEach>
	</table>
</body>
</html>