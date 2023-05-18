<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envio de Anexo</title>

<style>
div {
	border: 1px solid black;
	border-width: 1px;
	box-shadow: 2px;
	padding: 10px;
	width:25%;
	margin: 0 auto;	
	align:center;
}

h1 {
	padding:20px;
	text-align: center;
}

img[src="logo_cassi.png"] {
	

}

img[src="cloud_image.png"] {

}

img[src="logo_caurn.png"] {

}
</style>

</head>
<body>


<img src="logo_cassi.png" width="100" height="60" align="left" vspace="10px" hspace="15px">

<img src="logo_caurn.png" width="130" height="auto"align="right" >





<h1>Envio de arquivos de resposta CASSI</h1>


<br>
<br>

<div>
<h3>Selecione o arquivo DVL</h3>
<form action="UploadServletDVL" method="post" enctype="multipart/form-data">
  <input type="file" name="fileDVL">
  <input type="submit" value="Enviar">
</form>
</div>

<br>
<br>

<div>
<h3>Selecione o arquivo ERR</h3>
<form action="UploadServletERR" method="post" enctype="multipart/form-data">
  <input type="file" name="fileERR">
  <input type="submit" value="Enviar">
</form>
</div>

</body>
</html>