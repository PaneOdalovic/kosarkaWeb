<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<c:if test="${!empty utakmice}">
	<table id="centar">
	<tr><th>Mesto odrzavanja</th><th>Protivnicki klub</th><th>Datum Odrzavanja</th><th>Broj postignutuh poena</th><th>Broj primljenih poena</th><th>Napravi izvestaj</th></tr>
	
		<c:forEach var="u" items="${utakmice}">
			<tr>
				<td>${u.mestoOdrzavanje }</td>
				<td>${u.protivnickKlub }</td>
				<td>${u.datumOdrzavanja }</td>
				<td>${u.brojPostignutihPoena }</td>
				<td>${u.brojOrimljenihPoena }</td>
				<td>
					<form action="/KosarkaWeb/izvestaji/izvestajSaUtakmice.pdf" method="get" >
						<input type="hidden" value="${u.idutakmica }" name="id"/>
						<input type="submit" value="izvestaj"/>
					</form>
				</td>
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