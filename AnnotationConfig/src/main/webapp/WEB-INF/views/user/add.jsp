<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add User Annotation</title>
</head>
<body style="margin-left: 100px">

<%--
<form:form></form:form>
<form:input path=""></form:input>
<form:button></form:button>
<form:checkbox path=""></form:checkbox>
<form:checkboxes path="" items=""></form:checkboxes>
<form:option value=""></form:option>
<form:options></form:options>
<form:radiobutton path=""></form:radiobutton>
<form:radiobuttons path=""></form:radiobuttons>
<form:label path=""></form:label>
<form:password path=""></form:password>
<form:select path=""></form:select>
<form:textarea path=""></form:textarea>
<form:hidden path=""></form:hidden>
<form:textarea path=""></form:textarea>
--%>

<div><h1>Register Form</h1></div>
<c:url value="/user/add" var="url"/>
<%--@elvariable id="user" type="bht.models.User"--%>
<form:form modelAttribute="user" method="post" action="${url}" autocomplete="false">

    <br>
    <div>
        <label for="id">Student ID</label>
        <div>
            <form:input id="id" type="text" autocomplete="false"
                        placeholder="input student ID" path="id"/>

            &nbsp;&nbsp;
            <form:errors path="id" cssStyle="color: red; font-style: italic"/>
        </div>
    </div>

    <br>
    <div>
        <label for="username">Username</label>
        <div>
            <form:input id="username" type="text" autocomplete="false"
                        placeholder="input username" path="username"/>

            &nbsp;&nbsp;
            <form:errors path="username" cssStyle="color: red; font-style: italic"/>
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
        <form:radiobutton path="acceptAgreement" value="true"
                          label="Accept Our Agreement"/>

        &nbsp;&nbsp;
        <form:errors path="acceptAgreement" cssStyle="color: red; font-style: italic"/>
    </div>

    <br>
    <div>
        <form:button id="submit" type="submit">Register</form:button>
    </div>

</form:form>

</body>
</html>