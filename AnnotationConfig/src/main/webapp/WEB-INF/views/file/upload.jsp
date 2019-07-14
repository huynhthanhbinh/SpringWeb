<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Demo Upload File</title>
</head>
<body>


<div><h1>Form Upload File</h1></div>
<c:url value="/file/upload" var="url"/>


<form method="post" action="${url}"
      enctype="multipart/form-data">

    <br>
    <div>
        <input type="file" name="fileUpload" autocomplete="false">
    </div>

    <br>
    <div>
        <button id="submit" type="submit">Upload</button>
    </div>

</form>

</body>
</html>
