<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Login Page</title> </head>
<body>
<form name="actionForm" action="register" method ="POST">
<table>
<tr><td>Enter your Full Name: </td><td><input type="text" name="fullName"/></td></tr>
<tr><td>Enter your Username: </td><td><input type="text" name="uname"/></td></tr>
<tr><td>Enter your Password: </td><td><input type="password" name="password"/></td></tr>
<tr><td>Confirm your Password: </td><td><input type="password" name="confirm"/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>
</body>
</html>