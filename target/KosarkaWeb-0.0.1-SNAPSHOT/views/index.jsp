<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>