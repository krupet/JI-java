<%--
  Created by IntelliJ IDEA.
  User: krupet
  Date: 09.02.2015
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Delete</title>
</head>
<body>
<h1>Users</h1>
<p>You have deleted a user with id ${id} at <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/users" />
<p>Return to <a href="${mainUrl}">Main List</a></p>
</body>
</html>