<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Football Manager - Login Page</title> </head>
<body>
<c:remove var="selectedTeam" scope="session"/>
<%
if(null != session.getAttribute("flashMessage")){   
	String strExpired = (String) session.getAttribute("flashMessage");
	if (strExpired.equals("mustlogin")){     
		out.print("<h1>you must login to view that page</h1>");
		// remove flash message
		session.removeAttribute("flashMessage");
	}
	if (strExpired.equals("loginfail")){     
		out.print("<h1>Login failed. Please try again.</h1>");
		// remove flash message
		session.removeAttribute("flashMessage");
	}
	if (strExpired.equals("logout")){     
		out.print("<h1>You have logged out</h1>");
		// remove flash message
		session.removeAttribute("flashMessage");
	}
} 
%>
<form name="actionForm" action="login" method ="POST">
<table>
<tr><td>Enter your Username: </td><td><input type="text" name="uname"/></td></tr>
<tr><td>Enter your Password: </td><td><input type="password" name="password"/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>
<h2><a href="RegisterPage.jsp">Register</a></h2>
</body>
</html>