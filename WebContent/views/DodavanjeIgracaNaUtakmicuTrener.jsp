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
	
	<c:if test="${korisnik.jeTrener}">
	<table id="meni"><tr>
	<td>
	<form action="/KosarkaWeb/utakmice/ListaOdigranihUtakmicaTrener" method="get" >
			<input type="submit" value="Odigrane Utakmice"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/utakmice/ListaUtakmicaTrener" method="get" >
			<input type="submit" value="Neodigrane Utakmice"/>
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
	<c:if test="${!empty igraciNaUtakmici}">
	<div style="
	float:left;
	width:50%;
	">
	<table id="centar">
	<p><h2>Spisak igraca koji su na utakmici:</h2></p>
	<tr><th>Ime</th><th>Prezime</th><th>Datum Rodjenja</th><th>Datum Zaposljavanja</th><th>Broj Dresa</th></tr>
	
		<c:forEach var="i" items="${igraciNaUtakmici}">
			<tr>
				<td>${i.imeIgraca }</td>
				<td>${i.prezimeIgraca }</td>
				<td>${i.datumRodjenja }</td>
				<td>${i.datumZaposljavanja }</td>
				<td>${i.brojDresa }</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	</c:if>
	<c:if test="${!empty igraci}">
	<table id="centar">
	<p><h2>Spisak igraca:</h2></p>
	<tr><th>Ime</th><th>Prezime</th><th>Datum Rodjenja</th><th>Datum Zaposljavanja</th><th>Broj Dresa</th><th>Prva petorka</th><th>Dodaj</th></tr>
	
		<c:forEach var="i" items="${igraci}">
			<tr>
				<td>${i.imeIgraca }</td>
				<td>${i.prezimeIgraca }</td>
				<td>${i.datumRodjenja }</td>
				<td>${i.datumZaposljavanja }</td>
				<td>${i.brojDresa }</td>
				<form action="/KosarkaWeb/igraci/dodajIgracaNaUtakmicu" method="post" >
					<input type="hidden" value="${i.idigrac }" name="id"/>
					<td><input type="checkbox"  name="petorka" dafault="off"></td>
					<td><input type="submit" value="dodaj"/></td>
				
					</form>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	</c:if>
	</c:if>
	
	<c:if test="${korisnik.jeMenadzer}">
		<p>Nisi ulogovan kao trener</p>
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