<%--
  Created by IntelliJ IDEA.
  User: krupet
  Date: 09.02.2015
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Added</title>
</head>
<body>
<h1>Users</h1>
<p>You have added a new user at <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/userss" />
<p>Return to <a href="${mainUrl}">Main List</a></p>
</body>
</html>
