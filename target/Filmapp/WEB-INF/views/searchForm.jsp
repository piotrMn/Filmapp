<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#datepick").datepicker({
			dateFormat: 'dd-mm-yy',
			inline: true,
			showOtherMonths: true,
			dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
		
		});
	});
</script>
<style><%@include file="/WEB-INF/css/datepicker.css"%></style>
</head>
<body>
	<div>
		<table>
			<form:form method="post" modelAttribute="searchQuery">
				<tr>
					<td>
						Miasto
					</td>
					<td>
						<form:input type="text" path="town" />
						<form:errors path="town"/>
					</td>
				</tr>
				<tr>
					<td>
						Film
					</td>
					<td>
						<form:input type="text" path="title" />
						<form:errors path="title"/>
					</td>
				</tr>
				<tr>
					<td>
						Data
					</td>
					<td>
						<form:input id="datepick" type="text" path="date" />
						<form:errors path="date"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Szukaj"/></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>