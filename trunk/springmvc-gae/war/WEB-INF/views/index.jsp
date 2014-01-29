<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bom Dia Kim</title>
</head>
<body>
	<%-- <c:if test="$(empty param.txtUser)">
		<c:out value="Tiaguinho doido" />
	</c:if>
	<c:if test="$(empty param.txtUser)">
		<c:out
			test="$(param.txtUser == 'admin' and param.txtPassword == 'admin')">
			<jsp:forward page="ok.jsp" />
	</c:if>
	--%>
	<form method="POST">
		<table>
			<tr>

				<td>Username</td>
				<td><input type="text" name="txtUser" /></td>
			</tr>
			<tr>

				<td>Password</td>
				<td><input type="text" name="txtPassword" /></td>
			</tr>
			<tr>

				<td></td>
				<td><input type="Submit" value="Login" style="width: 156px;" /></td>
			</tr>
		</table>
	</form>
</body>
</html>