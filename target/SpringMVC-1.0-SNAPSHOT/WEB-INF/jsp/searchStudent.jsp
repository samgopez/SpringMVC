<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sam
  Date: 01/12/2016
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Student</title>
</head>
<body>
    <div align="center">
        <h1>Search Student</h1>
        <form action="/searchStudentByName" method="post">
            <input type="text" name="searchValue" />
            <input type="submit" value="Search" />
        </form>
        <table border="1">
            <th>ID</th>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Address</th>
            <th>Action</th>

            <c:forEach var="student" items="${studentList}" varStatus="status">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.givenName}</td>
                    <td>${student.middleName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.age}</td>
                    <td>${student.address}</td>

                    <td>
                        <a href="/editStudent/${student.id}">Edit</a>
                        <a href="/deleteStudent/${student.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/viewStudentList">View Student List</a>
    </div>
</body>
</html>
