<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${!empty korisnik}">
		
	<c:if test="${korisnik.jeMenadzer}">
	<table><tr>
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
	<form action="/KosarkaWeb/igra/korisnik" method="get" >
			<input type="submit" value="Pocetna"/>
	</form>
	</td>
	<td>
	<form action="/KosarkaWeb/igra/odjava" method="get" >
			<input type="submit" value="odjavi se"/>
	</form>
	</td>
	
	<td>${korisnik.ime} ${korisnik.prezime }</td>
	</tr></table>
	<c:if test="${!empty igraci}">
	<table border="1">
	<tr><th>Ime</th><th>Prezime</th><th>Broj Dresa</th><th>Broj poena</th><th>Promeni poene</th><th>Sacuvaj promenu</th></tr>
		<c:forEach var="i" items="${igraci}">
			<tr>
				<td>${i.igrac.imeIgraca }</td>
				<td>${i.igrac.prezimeIgraca }</td>
				<td>${i.igrac.brojDresa }</td>
				<td>${i.brojPoena }</td>
				<form action="/KosarkaWeb/igra/dodajRezultat" method="post" >
					<td><input type="hidden"  value="${i.brojPoena }" name="prethodni"/>
					<input type="hidden" value="${i.igrac.idigrac }" name="id"/>
					<input type="text"  name="brojPoena" ></td>
					<td><input type="submit" value="dodaj"/></td>
				</form>
			</tr>
		</c:forEach>
		</table>
		
		<br/>
		<form action="/KosarkaWeb/igra/dodajPoeneProtivnika" method="post" >
			<input type="text"  name="brojPoena" >
		<input type="submit" value="Dodaj poene protivnicke ekipe"/>
				</form>
		<p>Broj postignutih poena: ${brp }</p>
		<p>Broj primljenih poena: ${protivnik}</p>
		<form action="/KosarkaWeb/igra/sacuvajUtakmicrez" method="post" >
		<input type="submit" value="Uneti rezultati"/>
				</form>
		
		<p>${poruka }</p>
		
	</c:if>
	<c:if test="${empty igraci}">
		<p>Trener nije uneo spisak igraca za trazenu utakmicu!<p>
	</c:if>
	</c:if>
	</c:if>
	<c:if test="${!korisnik.jeMenadzer}">
		<p>Nisi ulogovan kao menadzer</p>
		<form action="/KosarkaWeb/igra/korisnik" method="get" >
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