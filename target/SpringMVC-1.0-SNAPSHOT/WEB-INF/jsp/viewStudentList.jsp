<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sam
  Date: 01/12/2016
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
    <div align="center">
        <h1>Student List</h1>
        <a href="/addStudent">Add New Student</a>
        <a href="/searchStudent">Search Student</a>
        <br /><br />
        <form action="/sortStudentList" method="post">
            Sort by:
            <select name="sortValue">
                <option value="id">ID</option>
                <option value="givenName">First Name</option>
            </select>
            <input type="submit" value="Submit" />
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
    </div>
</body>
</html>
