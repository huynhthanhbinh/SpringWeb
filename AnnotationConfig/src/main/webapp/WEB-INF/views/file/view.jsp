<%--@elvariable id="file" type="org.springframework.web.multipart.MultipartFile"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>View Upload File</title>
</head>
<body>

<div><h1>File Upload Information</h1></div>

<table border="1" cellpadding="10" cellspacing="5">
    <tr>
        <th style="text-align: right">Original Filename :</th>
        <td>${ file.originalFilename }</td>
    </tr>

    <tr>
        <th style="text-align: right">Filename :</th>
        <td>${ file.name }</td>
    </tr>

    <tr>
        <th style="text-align: right">Content Type :</th>
        <td>${ file.contentType }</td>
    </tr>

    <tr>
        <th style="text-align: right">Size in Bytes :</th>
        <td>${ file.size }</td>
    </tr>
</table>

</body>
</html>
