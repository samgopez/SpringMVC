<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sam
  Date: 01/12/2016
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
    <div align="center">
    <h1>Edit Student</h1>
    <h2>${message}</h2>
        <form:form action="/updateStudent" method="post">
            <table>
                <tr>
                    <td></td>
                    <td><form:hidden path="id" /></td>
                </tr>
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
                    <td><input type="submit" value="Update"></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>
