<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Rezerwuj</title>
	<style><%@include file="/WEB-INF/css/reservation.css"%></style>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script type="text/javascript" charset="UTF-8"><%@include file="/WEB-INF/js/reservationForm.js"%></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
</head>
<body>
	<div id="container" style="padding: 2em;">
		<div class="row">
			<div class="col-lg-6">
				<form method="post" action="http://localhost:8080/Filmapp/reserve/form">
					<table class="table-bordered" style="width: 80%;">
						<tr>
							<td>Tytuł</td>
							<td><c:out value="${screening.getFilm().getTitle() }"/></td>
						</tr>
						<tr>
							<td>Kino</td>
							<td><c:out value="${screening.getCinema().getName() }"/></td>
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
								<input id="seats-input" type="text" value="0" name="numberofseats" readonly/>
								<input id="seats-codes" type="hidden" name="seatcodes">
								<input id="screeningId" type="hidden" name="screeningId" value="${screening.getId() }">
							</td>
						</tr>
						<tr>
							<td>Razem</td>
							<td>
								<span id="price-total">0</span> zł
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="Wyślij" /></td>
							<c:url var="home" value="http://localhost:8080/Filmapp"/>
							<td><a href="${home }" class="btn btn-default">Anuluj</a></td>
						</tr>
						<c:if test="${not empty error}">
							<tr>
								<td colspan="2">Brak dostępnych miejsc!</td>
							</tr>
						</c:if>
					</table>
				</form>
			</div>
			<div class="col-lg-6">
				<h4>Wybrane miejsca</h4>
				<ul id="booked-seats">
				</ul>
			</div>
		</div>
	</div>

	<div style="width: 100%;">
		<p>Wybierz miejsca (max 6):</p>
		<table id="seats-table" class="table table-bordered">
			<tr style="text-align: center;">
				<td colspan="${screening.getCinema().getSeatsInRow() }">EKRAN</td>
			</tr>
			<%int i = 1; %>
			<c:forEach var="row" items="${bookedseats }">
			<tr data-row="<%=i %>">
				<c:forEach var="seat" begin="1" end="${fn:length(row)}">
					<c:set var="from" value="${seat - 1 }"/>
					<c:set var="to" value="${seat }"/>
					<c:if test="${fn:substring(row, from, to) == 0 }">
						<td class="free" data-seat="${seat }"></td>
					</c:if>
					<c:if test="${fn:substring(row, from, to) == 1 }">
						<td class="booked-before" data-seat="${nr }"></td>
					</c:if>
				</c:forEach>
			</tr>
			<%i++; %>
			</c:forEach>
		</table>
	</div>
</body>
</html>