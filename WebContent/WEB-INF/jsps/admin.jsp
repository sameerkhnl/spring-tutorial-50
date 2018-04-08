<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sameerkhanal
  Date: 4/5/18
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin page</title>
        <c:url var="userscss" value="/static/css/users.css" scope="page"></c:url>
        <link href="${userscss}" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <b>Authenticated users only.</b>

        <table class="userstable">
            <thead>
                <th>username</th>
                <th>password</th>
                <th>email</th>
                <th>enabled</th>
                <th>authority</th>
            </thead>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.username}"></c:out></td>
                    <td><c:out value="${user.password}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.enabled}"></c:out></td>
                    <td><c:out value="${user.authority}"></c:out></td>

                </tr>

            </c:forEach>


        </table>


    </body>
</html>
