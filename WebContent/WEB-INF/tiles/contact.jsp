<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


<sf:form method="POST" modelAttribute="message">
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
    <input type="hidden" name="_eventId" value="send"/>

    <table class="formtable">
        <tr>
            <td class="label">Your name:</td>
            <td><sf:input type="text" value="${fromName}" class="control" path="name"/></br>
                <sf:errors path="name" cssClass="formerror"></sf:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Your email:</td>
            <td><sf:input type="text" value="${fromEmail}" class="control" path="email"/></br>
                <sf:errors path="email" cssClass="formerror"></sf:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Subject:</td>
            <td><sf:input type="text" class="control" path="subject"/></br>
                <sf:errors path="subject" cssClass="formerror"></sf:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Your message:</td>
            <td><sf:textarea class="control" path="content"></sf:textarea></br>
                <sf:errors path="content" cssClass="formerror"></sf:errors>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Send message"/></td>
        </tr>

    </table>

</sf:form>
