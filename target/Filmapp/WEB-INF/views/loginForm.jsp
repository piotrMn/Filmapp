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
			<form:form method="post" modelAttribute="user">
				<p>Wprowadź dane do logowania</p>
				<div>
					<form:input path="username" type="text"  placeholder="Nazwa użytkownika"/>
					<form:errors path="username" cssClass="error" />	
				</div>
				<div>
					<form:input path="password" type="password" id="passInput" placeholder="Hasło" />
					<form:errors path="password" cssClass="error" />
				</div>
				<div id="passInput">
					<input type="checkbox" onclick="showPassword()"/>Pokaż hasło
				</div>
				<div style="padding: 5px;">
					<input type="submit" value="Prześlij" id="submit" class="btn btn-md" style="background-color: #3a3a3a; color: white;"/>
				</div>
				<div style="padding: 5px;">
					<c:url var="home" value="http://localhost:8080/Filmapp/home"/>
					<a href="${home }" class="btn btn-md" style="background-color: #3a3a3a; color: white;">Anuluj</a>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>