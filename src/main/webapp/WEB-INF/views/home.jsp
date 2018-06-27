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
	<!-- css -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
	<style><%@include file="/WEB-INF/css/home.css"%></style>
	<style><%@include file="/WEB-INF/css/datepicker.css"%></style>
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
			<c:url value="http://localhost:8080/Filmapp/static/images/cinema.jpg" var="cinema" />
			<img alt="kino" src="${cinema}" width="300px" height="200px" class="center img-rounded">
		</div>
		<!-- Pasek menu -->
		<div class="topnav">
			<c:url var="home" value="http://localhost:8080/Filmapp/session/clean"/>
			<a href="${home }" id="main-btn">Strona główna</a> 
			<a href="#" id="rep-btn" class="topnav-btns">Repertuar</a> 
			<a href="#" id="contact-btn" class="topnav-btns">Kontakt</a> 
			<a href="#" id="about-btn" class="topnav-btns">O nas</a>
			<c:if test="${empty username }">
				<c:url var="signup" value="http://localhost:8080/Filmapp/user/signup" />
				<a href="${signup}">Zarejestruj się</a>
				<c:url var="login" value="http://localhost:8080/Filmapp/user/login" />
				<a href="${login}">Zaloguj się</a>
			</c:if>
			<c:if test="${not empty username }">
				<a href="#" id="past-btn">Historia</a>
				<a href="#" id="cancelled-btn">Odwołane</a>
				<a href="#" id="upcoming-btn">Moje rezerwacje</a>
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
			<form:form method="post" modelAttribute="searchQuery" action="../../Filmapp/">
				<div class="search-field">
					<form:select path="cinema" class="select" id="film-select">
						<form:option value="" label="Wybierz kino"/>
						<c:forEach items="${cinemas }" var="cinema">
							<form:option value="${cinema.getName() }" cssClass="dropdown-item"/>
						</c:forEach>
					</form:select>
				</div>
				<div class="search-field">
					<form:select path="title" class="select" id="title-select">
						<form:option value="" label="Wybierz film"/>
						<c:forEach items="${films }" var="film">
							<form:option value="${film.getTitle() }"/>
						</c:forEach>
					</form:select>
				</div>
				<div class="search-field">
					<div>
						<c:if test="${empty dateerror && not empty datevalue}">
							<form:input id="datepick" type="text" path="date" value="${datevalue}" style="color:black" />
							<form:errors path="date" />								
						</c:if>
						<c:if test="${empty dateerror && empty datevalue}">
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
				<div class="search-field">
					<input id="search-btn" type="submit" value="Szukaj" />
				</div>
				<div id="show-results" class="hide">
					<button>Powrót do wyników szukania</button>
				</div>
			</form:form>
			<div id="back-to-searchresults" class="hide">
				<c:if test="${not empty searchresults && searchresults != 'emptylist' && searchresults != 'noquery' }">
					<button id="back-to-searchresults-btn">Powrót do wyników wyszukiwania</button>
				</c:if>
			</div>
		</div>
		<!--  repertuar -->
		<div id="repertoire" class="hide menu-items">
			<table class="table table-bordered table-dark table-responsive" style="color: black;">
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
				<c:forEach items="${films}" var="film">
					<tr>
						<td>
							<c:url var="path" value="${film.getPoster() }"/>
							<img src="${path}" alt="poster" width="200px" height="150px">
						</td>
						<td style="width: 120px; text-align: center;"><c:set value="${film.getTitle() }" var="title"/><c:out value="${title}"/></td>
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
							<a href="#" class="btn btn-default rep-book">Rezerwuj</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- kontakt -->
		<div id="contact" class="hide menu-items">
			<p style="margin: 1em;">
				Rezerwacja Biletów Kinowych S.c.<br>
				ul. Parkowa 10<br>
				51-182 Wrocław
			</p>
		</div>
		<!-- o nas -->
		<div id="about" class="hide menu-items">
			<p style="margin: 1em;">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam blandit turpis purus. Pellentesque mollis bibendum nisl, ut blandit metus. Ut semper metus at leo pharetra, vitae pretium arcu fermentum. Donec at rutrum ante, a vestibulum nulla. Suspendisse vel risus a eros eleifend rhoncus in ac dolor. Cras tortor urna, tempor quis viverra in, maximus sed justo. 
			</p>
		</div>
		<!-- Wyniki wyszukania -->
		<div id="search-results" class="menu-items">
			<c:if test="${not empty searchresults && searchresults != 'emptylist' && searchresults != 'noquery'}">
				<div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Data</th>
								<th>Tytuł</th>
								<th>Kino</th>
								<th>Dostępność</th>
								<th>Cena</th>
								<th></th>
							</tr>
						</thead>
						<%int i = 1; %>
						<c:forEach items="${searchresults}" var="res">
							<tr class="result-row" data-row="<%=i %>">
								<td>
									<c:out value="${res.getTimestampFormatted() }"></c:out>
								</td>
								<td>
									<c:out value="${res.getFilm().getTitle() }" />
								</td>
								<td>
									<c:out value="${res.getCinema().getName() }" />
								</td>
								<td>
									<c:if test="${res.getCinema().getCapacity() - res.getBooked() == 0 }">
										Brak dostępnych biletów.
									</c:if>
									<c:if test="${res.getCinema().getCapacity() - res.getBooked() > 0 }">
										<c:set var="available" value="${res.getCinema().getCapacity() - res.getBooked() }" />
										<c:out value="${available }" />/<c:out value="${res.getCinema().getCapacity() }" />
									</c:if>
								</td>
								<td>
									<c:set value="${res.getPrice()}" var="price" /> <fmt:formatNumber value="${price}" type="number" minFractionDigits="2" maxFractionDigits="2" />
								</td>
								<td>
									<c:set var="contains" value="false" />
									<c:forEach var="upc" items="${upcoming}">
									  	<c:if test="${upc.getScreening().getId() eq res.getId()}">
											<c:set var="contains" value="true" />
										</c:if>
									</c:forEach>
									<c:if test="${not empty username && contains eq false }">
										<c:url value="http://localhost:8080/Filmapp/reserve/form"
											var="reserveUrl">
											<c:param name="screeningId" value="${res.getId()}" />
											<c:param name="username" value="${username}" />]
										</c:url>
										<a href="${reserveUrl }" class="btn-md">Rezerwuj</a>
									</c:if> 
									<c:if test="${not empty username && contains eq true }">
										Rezerwacja dokonana
									</c:if>
									<c:if test="${empty username }">
										<c:url value="http://localhost:8080/Filmapp/user/signup" var="reserveUrl">
											<c:param name="screeningtobook" value="${res.getId() }"/>
										</c:url>
										<a href="${reserveUrl }" class="btn-md">Rezerwuj</a>
									</c:if>
								</td>
							</tr>
							<%i++; %>
						</c:forEach>
						<tr>
							<c:url value="http://localhost:8080/Filmapp/sort/time" var="byTime"/>
							<td><a href="${byTime }" class="sort-button">SORTUJ</a></td>
							<c:url value="http://localhost:8080/Filmapp/sort/title" var="byTitle"/>
							<td><a href="${byTitle }" class="sort-button">SORTUJ</a></td>
							<c:url value="http://localhost:8080/Filmapp/sort/cinema" var="byCinema"/>
							<td><a href="${byCinema }" class="sort-button">SORTUJ</a></td>
							<td></td>
							<td></td>
							<td colspan="2"></td>
						</tr>
					</table>
					<c:if test="${fn:length(searchresults) gt 10 }">
						<div>
					        <button id="prev-btn" type="button" class="scroll-button">Poprzednie</button>
					        <button id="next-btn" type="button" class="scroll-button">Następne</button>
						</div>	
					</c:if>
				</div>
			</c:if>
			<c:if test="${not empty searchresults && searchresults == 'emptylist'}">
				<div style="display: inline-block; text-align: center; width: 80%;">
					<strong>Brak wyników</strong>
				</div>
			</c:if>
			<c:if test="${searchresults == 'noquery' }">
				<p></p>
			</c:if>
		</div>
		<!-- Anulowane rezerwacje -->
		<div id="cancelled-res" class="hide menu-items">
			<c:if test="${empty cancelled }">
				<strong>Brak odwołanych rezerwacji</strong>
			</c:if>
			<c:if test="${not empty cancelled }">
				<strong>Odwołane rezerwacje</strong>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Numer</th>
							<th>Data</th>
							<th>Tytuł</th>
							<th>Kino</th>
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
								<c:out value="${urs.getScreening().getTimestampFormatted() }" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getFilm().getTitle()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getName()}" />
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
		<div id="upcoming-res" class="hide menu-items">
			<c:if test="${empty upcoming }">
				<strong>Brak nadchodzących rezerwacji.</strong>
			</c:if>
			<c:if test="${not empty upcoming}">
				<strong>Nadchodzące rezerwacje</strong>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Data</th>
							<th>Tytuł</th>
							<th>Kino</th>
							<th>Ilość miejsc</th>
							<th>Miejsca</th>
							<th>Anuluj</th>
						</tr>
					</thead>
					<c:forEach items="${upcoming}" var="urs">
						<tr>
							<td>
								<c:out value="${urs.getScreening().getTimestampFormatted() }" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getFilm().getTitle()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getName()}" />
							</td>
							<td>
								<c:out value="${urs.getReservation().getNrOfSeats() }" />
							</td>
							<td>
								<c:out value="${urs.getReservation().getSeatDescribed() }" />
							</td>
							<td>
								<c:url value="http://localhost:8080/Filmapp/reserve/cancel" var="resDeleteUrl">
									<c:param name="res_id" value="${urs.getReservation().getId() }" />
									<c:param name="user_id" value="${urs.getUser().getId()}"/>
								</c:url> 
								<a href="${resDeleteUrl}" onclick="return confirm('Czy na pewno anulować?')">Anuluj</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<!-- Minione rezerwacje -->
		<div id="past-res" class="hide menu-items">
			<c:if test="${empty past }">
				<strong>Brak minionych rezerwacji</strong>
			</c:if>
			<c:if test="${not empty past }">
				<strong>Minione rezerwacje</strong>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Numer</th>
							<th>Data</th>
							<th>Tytuł</th>
							<th>Kino</th>
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
								<c:out value="${urs.getScreening().getTimestampFormatted() }" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getFilm().getTitle()}" />
							</td>
							<td>
								<c:out value="${urs.getScreening().getCinema().getName()}" />
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