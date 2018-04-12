<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(document).ready(function () {
        $('.username').focus();
    });

</script>


<h2>
    Create New Account
</h2>
<c:url var="accountcreatedurl" value="/createaccount"/>

<sf:form id="accountDetails" action="${accountcreatedurl}" method="POST"
         modelAttribute="user">
    <table class="createaccounttable">
        <tr>
            <td class="label">Username</td>
            <td><sf:input type="text" class="control username" path="username"/><br/>
                <sf:errors path="username" cssClass="createaccounterror"/></td>
        </tr>
        <tr>
            <td class="label">Name</td>
            <td><sf:input type="text" class="control" path="name"/><br/>
                <sf:errors path="name" cssClass="createaccounterror"/></td>
        </tr>
        <tr>
            <td class="label">Email</td>
            <td><sf:input type="text" class="control" path="email"/><br/>
                <sf:errors path="email" cssClass="createaccounterror"/></td>
        </tr>
        <tr>
            <td class="label">Password</td>
            <td><sf:input type="password" id="password" class="control"
                          path="password"/><br/> <sf:errors path="password"
                                                            cssClass="createaccounterror"/></td>
        </tr>
        <tr>
            <td class="label">Confirm Password</td>
            <td><input type="password" class="control"
                       id="confirmPassword"/>
                <div id="passwordValidationMsg"></div>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Create Account" id="submitBtn"/></td>
        </tr>

    </table>

</sf:form>
