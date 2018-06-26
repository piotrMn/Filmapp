<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Subscribe</title>
	<script type="text/javascript">
		function showPassword() {
			var passInput = document.getElementById("passInput");
			if (passInput.type === "password") {
				passInput.type = "text";
			} else {
				passInput.type = "password";
			}
		}
	</script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style><%@include file="/WEB-INF/css/login.css"%></style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div style="padding: 2em;">
		<div id="form">
			<c:url value="http://localhost:8080/Filmapp/user/delete" var="delete"/>
			<form action="${delete}" method="post">
				<p>Aby usunąć konto podaj hasło</p>
				<input type="hidden" name="userid" value="${userid}">
				<input type="password" name="password">
				<input type="submit" value="Usuwam konto"> 
			</form>
		</div>
		<c:url value="http://localhost:8080/Filmapp/" var="home"/>
		<a href="${home}">Anuluj</a>
	</div>
</body>
</html>