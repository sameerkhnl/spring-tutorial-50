<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<b>Authenticated users only.</b>

<table class="userstable">
    <thead>
        <th>username</th>
        <th>name</th>
        <th>email</th>
        <th>enabled</th>
        <th>authority</th>
    </thead>

    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.username}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.enabled}" /></td>
            <td><c:out value="${user.authority}" /></td>

        </tr>

    </c:forEach>

</table>

