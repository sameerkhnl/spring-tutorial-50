<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<c:choose>
    <c:when test="${offers.size() != 0}">
        <table class="offerstable">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Text</th>

            </tr>
            <c:forEach var="row" items="${offers}">
                <tr>
                    <td><c:out value="${row.user.name}"/></td>
                    <td><a href='<c:url value="/message?uid=${row.username}" />'><c:out value="contact"/></a></td>
                    <td><c:out value="${row.text}"/></td>
                </tr>
            </c:forEach>
        </table>

    </c:when>

    <c:otherwise>
        <b>There are no offers available currently.</b>
    </c:otherwise>
</c:choose>

<br/><br/>





