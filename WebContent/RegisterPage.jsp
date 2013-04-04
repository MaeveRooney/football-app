<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Football Manager - Register</title> </head>
<body>
<c:remove var="selectedTeam" scope="session"/>
<%
if(null == session.getAttribute("flashMessage")){  
	// no session  
}else{  
	String strExpired = (String) session.getAttribute("flashMessage");
	if (strExpired.equals("notregistered")){     
		out.print("<h3>registration failed. please try again</h3>");
		// remove flash message
		session.removeAttribute("flashMessage");
	}  
}      
%>
<form name="actionForm" action="register" method ="POST">
<table>
<tr><td>Enter your Full Name: </td><td><input type="text" name="fullName"/></td></tr>
<tr><td>Enter your Username: </td><td><input type="text" name="uname"/></td></tr>
<tr><td>Enter your Password: </td><td><input type="password" name="password"/></td></tr>
<tr><td>Confirm your Password: </td><td><input type="password" name="confirm"/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>
<h2><a href="LoginPage.jsp">Login</a></h2>
</body>
</html>