<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add User Annotation</title>
</head>
<body>

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

<form class="form-horizontal">

    <br>
    <div>
        <label for="id">Student ID</label>
        <div>
            <input id="id" type="text" placeholder="input student ID">
        </div>
    </div>


    <br>
    <div>
        <label for="username">Username</label>
        <div>
            <input id="username" type="text" placeholder="input username">
        </div>
    </div>


    <br>
    <div>
        <label for="password">Password</label>
        <div>
            <input id="password" type="password" placeholder="input password">
        </div>
    </div>


    <br>
    <div>
        <label for="email">Email</label>
        <div>
            <input id="email" type="text" placeholder="input email">
        </div>
    </div>


    <br>
    <div>
        <label>Hobbies</label>
        <div>
            <div>
                <label>
                    <input type="checkbox" id="coding">Coding
                </label>
            </div>
            <div>
                <label>
                    <input type="checkbox" id="singing">Singing
                </label>
            </div>
            <div>
                <label>
                    <input type="checkbox" id="swimming">Swimming
                </label>
            </div>
            <div>
                <label>
                    <input type="checkbox" id="dancing">Dancing
                </label>
            </div>
        </div>
    </div>


    <br>
    <div>
        <label>Accept Our Agreement
            <input type="checkbox" id="acceptAgreement">
        </label>
    </div>


    <br>
    <div>
        <div>
            <button id="submit" formmethod="post">Register</button>
        </div>
    </div>
</form>
</body>
</html>
