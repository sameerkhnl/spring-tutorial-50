<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${action == 'created'}">
        <c:out value="Offer created"/>
    </c:when>

    <c:when test="${action == 'updated'}">
        <c:out value="Offer updated"/>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}">Click here to see the list of offers.</a>
