<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<script type="text/javascript">
    function onLoad() {
    loadMessage();

    setInterval(loadMessage, 5000);
    }

    function updateValue(data) {
    $("#nummessages").text('(' + data.number + ')');
    }

    function loadMessage() {
    //alert("Document laoded");
    $.getJSON("<c:url value='/getmessages'/>", updateValue);
    }

    $(document).ready(onLoad);
    </script>

    <c:url var="createoffer" value="/createoffer"/>
    <c:choose>

        <c:when test="${hasOffer == false}">
            <a href="${createoffer}"><c:out value="Add a new offer"/></a>
        </c:when>

        <c:otherwise>
            <a href="${createoffer}"><c:out value="Edit or delete your offer"/></a>
        </c:otherwise>
    </c:choose>
    &emsp;
    <security:authorize access="isAuthenticated()">
        <a href="<c:url value='/messages'/>">Messages <span id="nummessages"></span></a>
    </security:authorize>

    <br/><br/>
    <security:authorize access="hasRole('ADMIN')">
        <c:url var="admin" value="/admin"/>
        <a href="${admin}"><c:out value="Admin"/></a>
    </security:authorize>
</security:authorize>
