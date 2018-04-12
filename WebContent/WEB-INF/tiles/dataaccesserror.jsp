<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Error:
<c:out value="${errMsg}"></c:out><br>
<c:out value="${exceptionClass}"></c:out><br>
 An unexpected error occurred while accesing the data. Please try again later.
