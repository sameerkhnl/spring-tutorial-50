<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilel" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sameerkhanal
  Date: 4/8/18
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title><tiles:insertAttribute name="title"/></title>
        <c:url var="maincss" value="/static/css/main.css" />
        <link href="${maincss}" type="text/css" rel="stylesheet" />
        <script type="text/javascript"
                src="<c:url value='/static/JS/jquery-3.3.1.min.js' />"></script>

        <tiles:insertAttribute name="includes" />
    </head>
    <body>
        <div class="header">
            <tiles:insertAttribute name="header" />
        </div>

        <div class="content">
            <tiles:insertAttribute name="content"/>
        </div>
        <hr/>
        <div class="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
