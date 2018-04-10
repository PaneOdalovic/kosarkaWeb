<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="<c:url value="/resources/css/Logovanje.css"/>"/>
</head>
<body>
	<div class="tekst">
	<form action="/KosarkaWeb/igra/provera" method="post" >
	<table>
	<tr>
	<td>
	username:
	</td>
	<tr>
	<td>
	<input type="text"  name="username" />
	</td>
	</tr>
	<tr>
	<td>
	password:
	</td>
	</tr>
	<tr>
	<td>
	<input type="password"  name="password" />
	</td>
	</tr>
	</table>
	<input type="submit" value="uloguj se"/>
	</form>
	<p>${poruka}</p>
	</div>
</body>
</html>