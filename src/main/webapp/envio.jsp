<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envio de Anexo</title>
</head>
<body>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
  <input type="file" name="file">
  <input type="submit" value="Enviar">
</form>
</body>
</html>