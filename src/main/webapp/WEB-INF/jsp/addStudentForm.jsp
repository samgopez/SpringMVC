<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sam
  Date: 01/12/2016
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <div align="center">
        <h1>Add Student</h1>
        <form:form action="saveStudent" method="post">
            <table>
                <h2>${message}</h2>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="givenName" /></td>
                </tr>
                <tr>
                    <td>Middle Name:</td>
                    <td><form:input path="middleName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><form:input path="age" /></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><form:input path="address" /></td>
                </tr>
                <tr align="center">
                    <td><a href="/viewStudentList">View Student List</a> </td>
                    <td><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>
