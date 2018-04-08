<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilel" uri="http://tiles.apache.org/tags-tiles" %>
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
    </head>
    <body>
        <div>
            <tiles:insertAttribute name="header"/>
        </div>
        <div>
            <tiles:insertAttribute name="content"/>
        </div>
        <div>
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
