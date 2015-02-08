<%--
  Created by IntelliJ IDEA.
  User: krupet
  Date: 09.02.2015
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<h1>Create New User</h1>
<c:url var="saveUrl" value="/users/add" />
<form:form modelAttribute="userAttribute" method="POST" action="${saveUrl}">
  <table>
    <tr>
      <td><form:label path="name">Name:</form:label></td>
      <td><form:input path="name"/></td>
    </tr>

    <tr>
      <td><form:label path="nickName">Nick Name</form:label></td>
      <td><form:input path="nickName"/></td>
    </tr>
  </table>

  <input type="submit" value="Save" />
</form:form>
</body>
</html>
