<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Demo Annotation</title>
</head>
<body>
    <h1>
        Welcome,
        <span style="color: lightseagreen">
            <b><u><i>${username}</i></u></b>
        </span> !
    </h1>

    <h1 style="color: red">Hello ${person.name} !</h1>
    <h1 style="color: red">Your age is ${person.age} !</h1>
    <br>

    <img src="<c:url value='/resources/images/corgi.jpeg'/>" alt="corgi.jpeg"/>
</body>
</html>