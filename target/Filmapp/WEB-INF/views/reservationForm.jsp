<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Subscribe</title>
	<style><%@include file="/WEB-INF/css/reservation.css"%></style>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script type="text/javascript" charset="UTF-8"><%@include file="/WEB-INF/js/reservationForm.js"%></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
</head>
<body>
	<div id="container" style="padding: 2em;">
		<form method="post" action="http://localhost:8080/Filmapp/reserve/form">
			<table class="table-bordered" style="width: 40%;">
				<tr>
					<td>Tytuł</td>
					<td><c:out value="${screening.getFilm().getTitle() }"/></td>
				</tr>
				<tr>
					<td>Kino</td>
					<td><c:out value="${screening.getCinema().getName() }"/></td>
				</tr>
				<tr>
					<td>Miasto</td>
					<td><c:out value="${screening.getCinema().getTown() }"/></td>
				</tr>
				<tr>
					<td>Data</td>
					<td><fmt:formatDate value="${screening.getTimestamp() }" type="date" pattern="dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td>Godzina</td>
					<td><fmt:formatDate value="${screening.getTimestamp() }" type="time" pattern="HH:mm" /></td>
				</tr>
				<tr>
					<td>Cena</td>
					<td>
						<span id="price"><fmt:formatNumber value="${screening.getPrice() }" type="number" minFractionDigits="2" maxFractionDigits="2"/> zł</span>
					</td>
				</tr>
				<tr>
					<td>Ilość</td>
					<td>
						<input id="seats-input" type="number" value="1" min=1 max=6 step=1 name="seats" size="5"/>
					</td>
				</tr>
				<tr>
					<td>Razem</td>
					<td>
						<span id="price-total"><fmt:formatNumber value="${screening.getPrice() }" type="number" minFractionDigits="2" maxFractionDigits="2"/></span> zł
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Wyślij" />
					</td>
				</tr>
				<c:if test="${not empty error }">
					<tr>
						<td colspan="2">Brak dostępnych miejsc!</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>