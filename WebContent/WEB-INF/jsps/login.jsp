<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="myContext" value="${pageContext.request.contextPath}"></c:set>
<c:url var="createAccountUrl" value="/newaccount" />
<html>
<head>
<title>Login Page</title>
<link href="${myContext}/static/css/login.css" rel="stylesheet"
	type="text/css" />
</head>

<body onload="document.f.username.focus();">

	<h3>Login with Username and Password</h3>
	<c:if test="${param.error != null}">
		<div class="loginErrorMsg">Invalid username or password. Please
			try again.</div>
	</c:if>
	<form name='f' action="${myContext}/login" method='POST'>
		<table class="loginTable">
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</table>
	</form>
	
	<p><a href="${createAccountUrl}" >Create new account</a></p>
</body>
</html>