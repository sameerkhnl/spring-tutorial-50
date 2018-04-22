<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<script type="text/javascript">
    $(document).ready(onReady);

    function onDeleteClick(event) {
       var doDelete = confirm('Are you sure you want to delete this offer?');
       if(doDelete === false) {
           event.preventDefault();
       }
    }

    function onReady() {
        $('#delete').click(onDeleteClick);
    }

</script>


<c:choose>
    <c:when test="${offer.id == 0}">
        <c:set var="submitBtnVal" value="Create advert"/>
    </c:when>
    <c:otherwise>
        <c:set var="submitBtnVal" value="Update advert"/>
    </c:otherwise>
</c:choose>

<sf:form method="post" action="${pageContext.request.contextPath}/docreate"
         modelAttribute="offer">
    <sf:input path="id" type="hidden" />
    <table class="formtable">
        <tr>
            <td class="label">offer description:</td>
            <td><sf:textarea rows="10" columns="10" name="text"
                             class="control" path="text"></sf:textarea><br/><sf:errors path="text"
                                                                                       cssClass="formerror"></sf:errors>
            </td>
        </tr>

        <tr>
            <td class="label"></td>
            <td><input type="submit" value="${submitBtnVal}"
                       class="control btn"/></td>
        </tr>

        <c:if test="${offer.id != 0}">
            <tr>
                <td class="label"></td>
                <td><input type="submit" name="delete" class="control" value="Delete offer" id="delete"/></td>
            </tr>
        </c:if>
    </table>
</sf:form>
