<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Lista wyników:</h2>
	<table>
		<tr>
			<th>Data</th>
			<th>Godzina</th>
			<th>Tytuł</th>
			<th>Kino</th>
			<th>Miasto</th>
			<th>Dostępność</th>
			<th colspan="2">Akcje</th>
		</tr>
		<c:forEach items="${searchresults}" var="res">
			<tr>
				<td>
					<fmt:formatDate value="${res.getTimestamp() }" type="date" pattern="dd/MM/yyyy" />
				</td>
				<td>
					<fmt:formatDate value="${res.getTimestamp() }" type="time" pattern="HH:mm" />
				</td>
				<td>
					<c:out value="${res.getFilm().getTitle() }"/>
				</td>
				<td>
					<c:out value="${res.getCinema().getName() }"/>
				</td>
				<td>
					<c:out value="${res.getCinema().getTown() }"/>
				</td>
				<td>
					<c:set var="avail" value="${res.getCinema().getCapacity() - res.getBooked() }"/>
					<c:out value="${avail }"/>/<c:out value="${scr.getCinema().getCapacity() }"/>
				</td>
				<td>
					<c:url value="http://localhost:8080/Filmapp/reserve" var="reservationUrl">
						<c:param name="screening_id" value="${res.getId() }"/>
					</c:url>
					<a href="${reservationUrl }">Rezerwuj</a>
				</td>
				<td>
					<c:url value="http://localhost:8080/Filmapp/screening" var="screeningDetailUrl">
						<c:param name="screening_id" value="${res.getId() }"/>
					</c:url>
					<a href="${screeningDetailUrl }">Szczegóły</a>
				</td>
					
			</tr>
		</c:forEach>
	</table>
</body>
</html>