<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<c:choose>
    <c:when test="${offers.size() != 0}">
        <table class="offerstable">
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Text</th>

            </tr>
            <c:forEach var="row" items="${offers}">
                <tr>
                    <td><c:out value="${row.id}" /></td>
                    <td><c:out value="${row.username}" /></td>
                    <td><c:out value="${row.text}" /></td>
                </tr>
            </c:forEach>
        </table>

    </c:when>

    <c:otherwise>
        <b>There are no offers available currently.</b>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${hasOffer == true}">
        <p><a href="<c:url value='/createoffer' />">Edit/Delete your offer.</a></p>
    </c:when>
    <c:otherwise>
        <p><a href="<c:url value='/createoffer' />">add a new offer</a></p>
    </c:otherwise>
</c:choose>

<security:authorize access="hasRole('ADMIN')">
    <c:url var="adminpage" value="/admin" scope="page"/>
    <p><a href="${adminpage}">Admin</a></p>
</security:authorize>
