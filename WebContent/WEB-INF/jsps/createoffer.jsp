<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<body>

	<sf:form method="post" action="${pageContext.request.contextPath}/docreate"
		modelAttribute="offer">
		<table class="formtable">
			<tr>
				<td class="label">name:</td>
				<td><sf:input type="text" name="name" class="control"
						path="name" /><br/><sf:errors path="name" cssClass="error"></sf:errors></td>

			</tr>
			<tr>
				<td class="label">email:</td>
				<td><sf:input type="text" name="email" class="control"
						path="email" /><br/><sf:errors path="email" cssClass="error"></sf:errors></td>
			</tr>

			<tr>
				<td class="label">offer description:</td>
				<td><sf:textarea rows="10" columns="10" name="text"
						class="control" path="text"></sf:textarea><br/><sf:errors path="text" cssClass="error"></sf:errors></td>
			</tr>

			<tr>
				<td class="label"></td>
				<td><input type="submit" value="Create advert"
					class="control btn" /></td>
			</tr>
		</table>
	</sf:form>


</body>
</html>