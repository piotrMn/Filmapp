<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Witaj w aplikacji</title>
	<!-- javascript -->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8"><%@include file="/WEB-INF/js/home.js"%></script>
	<script type="text/javascript" charset="UTF-8"><%@include file="/WEB-INF/js/autocomplete.js"%></script>
	<!-- css -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
	<style><%@include file="/WEB-INF/css/home.css"%></style>
	<style><%@include file="/WEB-INF/css/datepicker.css"%></style>
	<style><%@include file="/WEB-INF/css/autocomplete.css"%></style>
</head>
<body>
	<div id="container">
		<!-- Powitanie użytkownika -->
		<c:if test="${not empty username}">
			<div id="hello-bar">
				<div>
					<c:set var="name" value="${username}" />
					Witaj, <c:out value="${name }"/>!
				</div>
			</div>
		</c:if>
		<!-- grafika -->
		<div id="picture">
			<c:url value="http://localhost:8080/Filmapp/static/images/cinema.jpg"
				var="cinema" />
			<img alt="kino" src="${cinema}" class="center img-rounded">
		</div>
		<!-- Pasek menu -->
		<div class="topnav">
			<a href="#" id="main-btn">Strona główna</a> 
			<a href="#" id="rep-btn">Repertuar</a> 
			<a href="#" id="cont-btn">Kontakt</a> 
			<a href="#" id="about-btn">O nas</a>
			<c:if test="${empty username }">
				<c:url var="signup" value="http://localhost:8080/Filmapp/user/signup" />
				<a href="${signup}">Zarejestruj się</a>
				<c:url var="login" value="http://localhost:8080/Filmapp/user/login" />
				<a href="${login}">Zaloguj się</a>
			</c:if>
			<c:if test="${not empty username }">
				<a href="#" id="past">Historia</a>
				<a href="#" id="cancelled">Odwołane</a>
				<a href="#" id="upcoming">Moje rezerwacje</a>
				<c:url var="logout" value="http://localhost:8080/Filmapp/user/logout" />
				<a href="${logout}" onclick="return confirm('Czy na pewno chcesz się wylogować?')">Wyloguj się</a>
				<c:url var="delete" value="http://localhost:8080/Filmapp/user/delete" >
					<c:param name="username" value="${username}"/>
				</c:url>
				<a href="${delete}" style="float: right;">Usuń konto</a>
			</c:if>
		</div>
		<!-- Formularz szukania filmów -->
		<div id="film-search">
			<div style="width: 100%; display: inline-block; text-align: center;">
				<form:form method="post" modelAttribute="searchQuery" action="../../Filmapp/home">
					<div class="autocomplete">
						<div>
							<c:if test="${empty townerror}">
								<form:input id="town-input" type="text" path="town" placeholder="Miasto" autocomplete="off" />
								<form:errors path="town" />							
							</c:if>
							<c:if test="${not empty townerror}">
								<form:input id="town-input" type="text" path="town" placeholder="${townerror}" autocomplete="off"/>
								<form:errors path="town" />	
							</c:if>
						</div>
						<div style="width: 150px;"></div>
					</div>
					<div class="autocomplete">
						<div>
							<c:if test="${empty titleerror }">
								<form:input id="title-input" type="text" path="title" placeholder="Tytuł filmu" autocomplete="off" />
								<form:errors path="title" />							
							</c:if>
							<c:if test="${not empty titleerror }">
								<form:input id="title-input" type="text" path="title" placeholder="${titleerror}" autocomplete="off" />
								<form:errors path="title" />							
							</c:if>
						</div>
						<div style="width: 150px;"></div>
					</div>
					<div style="display: inline-block;">
						<div>
							<c:if test="${empty dateerror}">
								<form:input id="datepick" type="text" path="date" placeholder="Data" />
								<form:errors path="date" />								
							</c:if>
							<c:if test="${not empty dateerror}">
								<form:input id="datepick" type="text" path="date" placeholder="${dateerror}"/>
								<form:errors path="date" />								
							</c:if>
						</div>
						<div style="width: 150px;"></div>
					</div>
					<div style="display: inline-block;">
						<input type="submit" value="Szukaj" />
					</div>
				</form:form>
			</div>
		</div>
		<!--  repertuar -->
		<div id="repertoire" class="hide table-responsive">
			<table class="table table-bordered table-dark" style="color: black;">
				<thead>
					<tr>
						<td></td>
						<td style="width: 120px; text-align: center;">Tytuł</td>	
						<td>Rok</td>	
						<td>Gatunek</td>
						<td>Reżyser</td>
						<td>Opis</td>
						<td></td>		
					</tr>
				</thead>
				<c:forEach items="${repertoire}" var="film">
					<tr>
						<td>
							<c:url var="path" value="${film.getPoster() }"/>
							<img src="${path}" alt="poster" width="200px" height="150px">
						</td>
						<td style="width: 120px; text-align: center;"><c:set value="${film.getTitle() }" var="title"/><c:out value="${title}"/>
						</td>
						<td>
							<c:out value="${film.getYear() }"/>
						</td>
						<td>
							<c:out value="${film.getGenre() }"/>
						</td>
						<td>
							<c:out value="${film.getDirector() }"/>
						</td>
						<td>
							<c:out value="${film.getDescription() }"/>
						</td>
						<td>
							<button class="btn btn-md rep">Rezerwuj</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- kontakt -->
		<div id="contact" class="hide">
			<p style="margin: 1em;">
				Rezerwacja Biletów Kinowych S.c.<br>
				ul. Parkowa 10<br>
				51-182 Wrocław
			</p>
		</div>
		<!-- o nas -->
		<div id="about" class="hide">
			<p style="margin: 1em;">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam blandit turpis purus. Pellentesque mollis bibendum nisl, ut blandit metus. Ut semper metus at leo pharetra, vitae pretium arcu fermentum. Donec at rutrum ante, a vestibulum nulla. Suspendisse vel risus a eros eleifend rhoncus in ac dolor. Cras tortor urna, tempor quis viverra in, maximus sed justo. 
			</p>
		</div>
		<!-- Wyniki wyszukania -->
		<c:if test="${not empty searchresults }">
			<div id="search-results">
				<table class="table">
					<thead>
						<tr>
							<th>Data</th>
							<th>Godzina</th>
							<th>Tytuł</th>
							<th>Kino</th>
							<th>Miasto</th>
							<th>Dostępność</th>
							<th>Cena</th>
							<th colspan="2">Akcje</th>
						</tr>
					</thead>
					<c:forEach items="${searchresults}" var="res">
						<tr>
							<td><fmt:formatDate value="${res.getTimestamp() }"
									type="date" pattern="dd/MM/yyyy" /></td>
							<td><fmt:formatDate value="${res.getTimestamp() }"
									type="time" pattern="HH:mm" /></td>
							<td><c:out value="${res.getFilm().getTitle() }" /></td>
							<td><c:out value="${res.getCinema().getName() }" /></td>
							<td><c:out value="${res.getCinema().getTown() }" /></td>
							<td><c:set var="avail"
									value="${res.getCinema().getCapacity() - res.getBooked() }" />
								<c:out value="${avail }" />/<c:out
									value="${res.getCinema().getCapacity() }" /></td>
							<td><c:set value="${res.getPrice()}" var="price" /> <fmt:formatNumber value="${price}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
							<td><c:if test="${not empty username }">
									<c:url value="http://localhost:8080/Filmapp/reserve/form"
										var="reserveUrl">
										<c:param name="scrId" value="${res.getId()}" />
										<c:param name="userId" value="${userId }" />]
									</c:url>
								</c:if> 
								<c:if test="${empty username }">
									<c:url value="http://localhost:8080/Filmapp/user/signup"
										var="reserveUrl" />
								</c:if> 
								<a href="${reserveUrl }" class="btn-md">Rezerwuj</a>
							</td>
							<td>
								<div class="show-details">
									<a class="btn-md" style="color: black;">Szczegóły</a>
								</div>
							</td>
						</tr>
						<!-- Szczegóły ukryte -->
						<tr class="hide">
							<td colspan="9">
								<div>
									Adres: <c:out value="${res.getCinema().getAddress() }"></c:out>
									<c:out value="${res.getCinema().getTown() }"></c:out><br>
									O filmie:<br>
									Reżyseria: <c:out value="${res.getFilm().getDirector() }"></c:out><br>
									Gatunek: <c:out value="${res.getFilm().getGenre() }"></c:out><br>
									<c:out value="${res.getFilm().getDescription() }"></c:out>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		<c:set value="${searchresults }" var="res"></c:set>
		<c:if test="${res != null && empty res}">
			<div style="display: inline-block; text-align: center; width: 80%;">
				<strong>Brak wyników dla powyższych danych.</strong>
			</div>
		</c:if>
		<!-- Anulowane rezerwacje -->
		<div id="cancelled-res" class="hide">
			<c:if test="${empty cancelled }">
				<div>Brak odwołanych rezerwacji</div>
			</c:if>
			<c:if test="${not empty cancelled }">
				<p>Odwołane rezerwacje</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Numer</th>
							<th>Data</th>
							<th>Godzina</th>
							<th>Tytuł</th>
							<th>Kino</th>
							<th>Miasto</th>
							<th>Ilość miejsc</th>
							<th>Cena</th>
						</tr>
					</thead>
					<c:forEach items="${cancelled}" var="urs">
						<tr>
							<td>
								<c:out value="${urs.getReservation().getResNumber() }"/>
							</td>
							<td>
								<fmt:formatDate value="${urs.getScreening().getTimestamp() }" type="date" pattern="dd/MM/yyyy" />
							</td>
							<td>
								<fmt:formatDate value="${urs.getScreening().getTimestamp() }" type="time" pattern="HH:mm" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getFilm().getTitle()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getName()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getTown() }" />
							</td>
							<td>
								<c:out value="${urs.getReservation().getNrOfSeats() }" />
							</td>
							<td>
								<c:set value="${urs.getReservation().getNrOfSeats() * urs.getScreening().getPrice() }" var="price"/>
								<fmt:formatNumber value="${price}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		
		<!-- Nadchodzące rezerwacje -->
		<div id="upcoming-res" class="hide">
			<c:if test="${empty upcoming }">
				<div>Brak nadchodzących rezerwacji.</div>
			</c:if>
			<c:if test="${not empty upcoming}">
				<p>Nadchodzące rezerwacje</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Data</th>
							<th>Godzina</th>
							<th>Tytuł</th>
							<th>Kino</th>
							<th>Miasto</th>
							<th>Ilość miejsc</th>
							<th>Anuluj</th>
						</tr>
					</thead>
					<c:forEach items="${upcoming}" var="urs">
						<tr>
							<td><fmt:formatDate
									value="${urs.getScreening().getTimestamp() }" type="date"
									pattern="dd/MM/yyyy" /></td>
							<td><fmt:formatDate
									value="${urs.getScreening().getTimestamp() }" type="time"
									pattern="HH:mm" /></td>
							<td><c:out
									value="${urs.getScreening().getFilm().getTitle()}" /></td>
							<td><c:out
									value="${urs.getScreening().getCinema().getName()}" /></td>
							<td><c:out
									value="${urs.getScreening().getCinema().getTown() }" /></td>
							<td><c:out value="${urs.getReservation().getNrOfSeats() }" /></td>
							<td>
								<c:url value="http://localhost:8080/Filmapp/reserve/cancel" var="resDeleteUrl">
									<c:param name="res_id" value="${urs.getReservation().getId() }" />
								</c:url> 
							<a href="${resDeleteUrl}" onclick="return confirm('Czy na pewno anulować?')">Anuluj</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<!-- Minione rezerwacje -->
		<div id="past-res" class="hide">
			<c:if test="${empty past }">
				<div>Brak minionych rezerwacji</div>
			</c:if>
			<c:if test="${not empty past }">
				<p>Minione rezerwacje</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Numer</th>
							<th>Data</th>
							<th>Godzina</th>
							<th>Tytuł</th>
							<th>Kino</th>
							<th>Miasto</th>
							<th>Ilość miejsc</th>
							<th>Cena</th>
						</tr>
					</thead>
					<c:forEach items="${past}" var="urs">
						<tr>
							<td>
								<c:out value="${urs.getReservation().getResNumber() }"/>
							</td>
							<td>
								<fmt:formatDate value="${urs.getScreening().getTimestamp() }" type="date" pattern="dd/MM/yyyy" />
							</td>
							<td>
								<fmt:formatDate value="${urs.getScreening().getTimestamp() }" type="time" pattern="HH:mm" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getFilm().getTitle()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getName()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getTown() }" />
							</td>
							<td>
								<c:out value="${urs.getReservation().getNrOfSeats() }" />
							</td>
							<td>
								<c:set value="${urs.getReservation().getNrOfSeats() * urs.getScreening().getPrice() }" var="price"/>
								<fmt:formatNumber value="${price}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>