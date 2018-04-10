<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unos stanara</title>
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
	
	<td>${korisnik.uloga }</td>
	
	</tr></table>
	<c:set var="today" value="<%=new Date()%>" />
	<form:form action="/KosarkaWeb/igraci/saveIgraca" method="post" modelAttribute="igrac">
		<table id="centar"> 
  			<tr><td>Ime:</td><td><form:input type="text" name="imeIgraca" path="imeIgraca"/></td></tr>
  			<tr><td>Prezime:</td><td><form:input type="text" name="prezimeIgraca" path="prezimeIgraca"/></td></tr>
  			<tr><td>BrojDresa</td><td><form:input type="text" name="brojDresa" path="brojDresa"/></td></tr>
  	   		<tr><td>Datum rodjenja:</td><td><input type="data" name="datumRodjenja"  path="datumRodjenja" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/></td></tr>
  	   		<tr><td>Datum zaposljavanja:</td><td><input type="date" name="datumZaposljavanja" path="datumZaposljavanja" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/></td></tr>
  			<tr><td></td><td><input type="submit" value="Sacuvaj"/></td></tr>
   		</table>
  	</form:form>
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