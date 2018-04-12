<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<script type="text/javascript">
		$(document).ready(function () {
           $('#username').focus();
        });
	</script>

	<c:url var="createAccountUrl" value="/newaccount" />
	<c:url var="loginurl" value="/login" />

	<h3>Login with Username and Password</h3>
	<c:if test="${param.error != null}">
		<div class="loginErrorMsg">Invalid username or password. Please
			try again.</div>
	</c:if>
	<form name='f' action="${loginurl}" method='POST'>
		<table class="logintable">
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value='' id="username"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>

			<tr>
				<td>Remember me:</td>
				<td><input type="checkbox" name="remember-me" checked="checked"/> </td>
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
