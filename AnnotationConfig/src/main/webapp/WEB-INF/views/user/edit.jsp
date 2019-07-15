<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Demo Edit User</title>
</head>
<body style="margin-left: 100px">

<div><h1>Edit User Form</h1></div>
<c:url value="/user/${user.id}/edit" var="url"/>
<%--@elvariable id="user" type="com.bht.models.User"--%>
<form:form modelAttribute="user" method="post" action="${url}"
           autocomplete="false" enctype="multipart/form-data">

    <br>
    <div>
        <label for="id">User ID (Auto-increment)</label>
        <div>
            <input id="id" type="number" value="${user.id}"
                   readonly name="id"
                   style="background-color: darkgrey"/>
        </div>
    </div>

    <br>
    <div>
        <label for="username">Username</label>
        <div>
            <input id="username" type="text" value="${user.username}"
                   readonly name="username"
                   style="background-color: darkgrey"/>
        </div>
    </div>

    <br>
    <div>
        <label for="password">Password</label>
        <div>
            <form:input id="password" type="password" autocomplete="false"
                        placeholder="input password" path="password"/>

            &nbsp;&nbsp;
            <form:errors path="password" cssStyle="color: red; font-style: italic"/>
        </div>
    </div>

    <br>
    <div>
        <label for="email">Email</label>
        <div>
            <form:input id="email" type="text" autocomplete="false"
                        placeholder="input email" path="email"/>

            &nbsp;&nbsp;
            <form:errors path="email" cssStyle="color: red; font-style: italic"/>
        </div>
    </div>

    <br>
    <div>
        <label>Hobbies</label><br>
            <%--@elvariable id="hobbies" type="java.util.List"--%>
        <form:checkboxes path="hobbies" items="${hobbies}" delimiter="<br>"/>
    </div>

    <br>
    <div>
        <label>Gender</label>
        <form:select path="gender">
            <form:option value="false">Female</form:option>
            <form:option value="true">Male</form:option>
        </form:select>
    </div>

    <br>
    <div>
        <label>Avatar</label>
        <div>
            <input type="file" name="fileUpload" autocomplete="false"
                   accept="image/*"> <!-- accept only image format -->
        </div>
    </div>

    <br>
    <div>
        <form:button id="submit" type="submit">Update</form:button>
    </div>

</form:form>

</body>
</html>
