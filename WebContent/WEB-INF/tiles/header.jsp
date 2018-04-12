<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="title" href="<c:url value='/' />">Offers</a>
<security:authorize access="!isAuthenticated()">
    <c:url var="loginpage" value="/login"></c:url>
    <a href="${loginpage}" class="login">Login</a>
</security:authorize>

<security:authorize access="isAuthenticated()">
    <c:url var="logoutUrl" value="/logout" scope="page"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out" class="logout"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</security:authorize>