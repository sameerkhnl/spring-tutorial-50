<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>home</title>
    </head>
    <body>

        <p><a href="${pageContext.request.contextPath}/offers">show current offers</a></p>
        <p><a href="${pageContext.request.contextPath}/createoffer">add a new offer</a></p>


        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout" scope="page"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            <c:url var="adminpage" value="/admin" scope="page"/>
            <p><a href="${adminpage}">Admin</a></p>
        </security:authorize>

        <security:authorize access="!isAuthenticated()">
            <c:url var="loginpage" value="/login"></c:url>
            <p><a href="${loginpage}">Login</a></p>
        </security:authorize>

    </body>
</html>