<%--@elvariable id="user" type="com.bht.models.User"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View User Annotation</title>
</head>
<body>

<div><h1>User's information</h1></div>

<table border="1" cellpadding="10" cellspacing="5">
    <tr>
        <th style="text-align: right">User ID :</th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th style="text-align: right">Username :</th>
        <td>${user.username}</td>
    </tr>
    <tr>
        <th style="text-align: right">Password :</th>
        <td>${user.password}</td>
    </tr>
    <tr>
        <th style="text-align: right">Email :</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th style="text-align: right; vertical-align: top">Hobbies :</th>
        <td>
            <c:forEach items="${user.hobbies}" var="item">
                <p>+ ${item}</p>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th style="text-align: right">Gender :</th>
        <td>${user.gender}</td>
    </tr>
    <tr>
        <th style="text-align: right">Accept :</th>
        <td>${user.acceptAgreement}</td>
    </tr>
    <tr>
        <th style="text-align: right">Avatar :</th>
        <td>
            <c:if test="${user.hasAvatar == false}">
                <img src="<c:url
                value='/resources/users/unknown.jpg'/>"
                     alt="Unknown Avatar"
                     title="Unknown Avatar"
                     width="200" height="200"/>
            </c:if>
            <c:if test="${user.hasAvatar == true}">
                <img src="<c:url
                value='/resources/users/${user.avatarPath}'/>"
                     alt="${user.id}'s Avatar"
                     title="${user.id}'s Avatar"
                     width="200" height="200"/>
            </c:if>
        </td>
    </tr>
</table>

</body>
</html>