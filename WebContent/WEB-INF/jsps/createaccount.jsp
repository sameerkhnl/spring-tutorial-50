<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<fmt:message var="matchedPasswordsMsg" key="MatchedPasswords.user.password" />
<fmt:message var="unmatchedPasswordsMsg" key="UnmatchedPasswords.user.password" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Account</title>
    <link href="<c:url value='/static/css/createaccount.css' />"
          type="text/css" rel="stylesheet"/>

    <script type="text/javascript"
            src="<c:url value='/static/JS/jquery-3.3.1.min.js' />"></script>

    <script type="text/javascript">
        (function () {
           $(document).ready(onLoad);

        var p1, p2;

        function onLoad() {
            $("#password").keyup(passwordsKeyUp);
            $("#confirmPassword").keyup(passwordsKeyUp);
            $("#accountDetails").submit(canSubmit);
        }

        function passwordsKeyUp() {

            p1 = $("#password").val();
            p2 = $("#confirmPassword").val();

            if (p1.length > 3 || p2.length > 3) {
                if (p1 != p2) {
                    //alert(p1 + ": " + p2);

                    //$("#passwordValidationMsg").removeClass("passwordsmatch");
                    $("#passwordValidationMsg").addClass("passwordsdonotmatch");
                    $("#passwordValidationMsg").text("${unmatchedPasswordsMsg}");
                } else {

                    $("#passwordValidationMsg").removeClass("passwordsdonotmatch");
                    $("#passwordValidationMsg").addClass("passwordsmatch");
                    $("#passwordValidationMsg").text("${matchedPasswordsMsg}");
                }
            }
        }

        function canSubmit() {
            if (p1 != p2) {
                alert("The passwords do not match...");
                return false;
            }
            return true;
        }
       })();

    </script>


</head>
<body>
<c:if test="${userCheckErrMsg!= null}">
    <p>
        <c:out value="${userCheckErrMsg}"/>
    </p>
</c:if>
<h2>
    Create New Account
</h2>
<c:url var="accountcreatedurl" value="/createaccount"/>

<sf:form id="accountDetails" action="${accountcreatedurl}" method="POST"
         modelAttribute="user">
    <table class="createAccountTable">
        <tr>
            <td class="label">Username</td>
            <td><sf:input type="text" class="control" path="username"/><br/>
                <sf:errors path="username" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Email</td>
            <td><sf:input type="text" class="control" path="email"/><br/>
                <sf:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Password</td>
            <td><sf:input type="password" id="password" class="control"
                          path="password"/><br/> <sf:errors path="password"
                                                            cssClass="error"/></td>
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
</body>
</html>