<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/meni.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/css/tabele.css"/>"/>
</head>
<body>
		<c:if test="${!empty korisnik}">
	
	<c:if test="${korisnik.jeMenadzer}">
		<table id="meni"><tr>
	<td>
	<form action="/KosarkaWeb/igraci/ListaIgraca" method="get" >
			<input type="submit" value="Tim"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/igra/ListaUtakmicaMenadzer" method="get" >
			<input type="submit" value="Utakmice"/>
	</form>
	</td>
	
	<td>
	<form action="/KosarkaWeb/igraci/UnosIgracaa" method="get" >
			<input type="submit" value="Dodaj igraca u tim"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/utakmice/utakmica" method="get" >
			<input type="submit" value="Dodaj utakmicu"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/views/Pocetna.jsp" method="get" >
			<input type="submit" value="Pocetna"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/igra/odjava" method="get" >
			<input type="submit" value="odjavi se"/>
	</form>
	</td>
	
	
	<td>${korisnik.uloga}</td>
	</tr></table>
	<c:if test="${!empty igraci}">
	<table id="centar">
	<tr><th>Ime</th><th>Prezime</th><th>Datum Rodjenja</th><th>Datum Zaposljavanja</th><th>Broj Dresa</th><th>Otpusti</th></tr>
		<c:forEach var="i" items="${igraci}">
			<tr>
				<td>${i.imeIgraca }</td>
				<td>${i.prezimeIgraca }</td>
				<td>${i.datumRodjenja }</td>
				<td>${i.datumZaposljavanja }</td>
				<td>${i.brojDresa }</td>
				<td><form action="/KosarkaWeb/igraci/odpusti" method="get" >
						<input type="hidden" value="${i.idigrac }" name="id"/>
						<input type="submit" value="odpusti"/>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<form action="/KosarkaWeb/izvestaji/izvestajTima.pdf" method="get" >
			<input type="submit" value="Napravi izvestaj celog tima"/>
	</form>
	</c:if>
	</c:if>
	<c:if test="${korisnik.jeTrener}">
		<p>Nisi ulogovan kao menadzer</p>
		<form action="/KosarkaWeb/views/Pocetna.jsp" method="get" >
			<input type="submit" value="Pocetna"/>
	</form>
	</c:if>
	<c:if test="${empty korisnik}">
		<p>Niste ulogavani</p>
		<form action="/KosarkaWeb/views/index.jsp" method="get" >
					<input type="submit" value="uloguj se"/>
		</form>
	</c:if>
	
</body>
</html>