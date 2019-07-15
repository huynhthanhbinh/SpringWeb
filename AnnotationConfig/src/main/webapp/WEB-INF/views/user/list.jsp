<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Demo User List</title>
</head>
<body>

<div><h1>Users' List</h1></div>
<hr>

<div>
    <table border="1" cellpadding="10" cellspacing="5">
        <tr style="text-align: center">
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Gender</th>
            <th>HasAvatar</th>
            <th>View</th>
        </tr>

        <%--@elvariable id="users" type="java.util.List"--%>
        <c:forEach items="${users}" var="user">
            <tr style="text-align: left">
                <th>${user.id}</th>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.gender}</td>
                <td>${user.hasAvatar}</td>

                <td>
                    <a href="<c:url value='/user/${user.id}'/>">
                        View user
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
