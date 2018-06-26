<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zarejestruj się</title>
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
<style><%@include file="/WEB-INF/css/signup.css"%></style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div id="container">
		<div class="signup">
			<h4>Wprowadź dane do rejestracji:</h4>
			<form:form method="post" modelAttribute="user">
				<table>
					<tr>
						<td class="names">Nazwa użytkownika</td>
						<td class="values">
							<form:input path="username" type="text" />
						</td>
						<td>
							<form:errors path="username" cssClass="error" />
						</td>
					</tr>
					<tr style="height: 10px;"></tr>
					<tr>
						<td class="names">Adres e-mail</td>
						<td class="values">
							<form:input path="email" type="text" />
						</td>
						<td>
							<form:errors path="email" cssClass="error" />
						</td>
					</tr>
					<tr style="height: 10px;"></tr>
					<tr>
						<td class="names">Hasło </td>
						<td class="values">
							<form:input path="password" type="password" id="passInput" />
						</td>
						<td>
							<form:errors path="password" cssClass="error" />
						</td>
					</tr>
					<tr style="height: 10px;"></tr>
					<tr>
						<td class="names">Powtórz hasło</td>
						<td class="values">
							<input name="password2" type="password"/>
						</td>
						<td>
						<c:if test="${not empty error }">
							Hasła nie zgadzają się!
						</c:if>
						</td>
					</tr>
					<tr style="height: 20px;"></tr>
					<tr>
						<td class="names">
							<input type="submit" value="Prześlij" id="submit" class="btn btn-md myButton"/>	
						</td>
						<td class="values">
							<c:url value="http://localhost:8080/Filmapp/user/login" var="loginUrl"/>
							<a href="${loginUrl }" class="btn btn-md myButton">Mam już konto</a>
						</td>
						<td>
							<div id="passInput">
								<input type="checkbox" onclick="showPassword()"/>Pokaż hasło
							</div>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>