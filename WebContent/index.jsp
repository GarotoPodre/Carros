<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE OF CAR SYSTEM</title>
</head>
<body>
	Hello Everybody! ;)
	<form action="<%=request.getContextPath()%>/hello" method="post">
	Nome <input type="text" name="nome" />
	Sobrenome <input type="text" name="sobrenome" />
	<input type="submit" name="GO!">
	</form>
</body>
</html>