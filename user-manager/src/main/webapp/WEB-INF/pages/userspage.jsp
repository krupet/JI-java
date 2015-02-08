<%--
  Created by IntelliJ IDEA.
  User: krupet
  Date: 08.02.2015
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Users page</title>
</head>
<body>
  <h1>Users</h1>
  <c:url var="addUrl" value="/users/add" />
  <table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
      <th>Name</th>
      <th>Nick-Name</th>
      <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
      <c:url var="editUrl" value="/users/edit?id=${user.id}" />
      <c:url var="deleteUrl" value="/users/delete?id=${user.id}" />
      <tr>
        <td><c:out value="${user.name}" /></td>
        <td><c:out value="${person.nickName}" /></td>
        <td><a href="${editUrl}">Edit</a></td>
        <td><a href="${deleteUrl}">Delete</a></td>
        <td><a href="${addUrl}">Add</a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <c:if test="${empty users}">
    There are currently no persons in the list. <a href="${addUrl}">Add</a> a user.
  </c:if>
</body>
</html>
