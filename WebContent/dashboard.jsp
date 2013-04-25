<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Football Manager - Dashboard</title> </head>
<body>
<c:remove var="selectedTeam" scope="session"/>
<%
if(null == session.getAttribute("user")){   
	session.setAttribute("flashMessage","mustlogin");
	response.sendRedirect("LoginPage.jsp");
} else if(null != session.getAttribute("name")){
	String fullname = (String) session.getAttribute("name");
	out.print("welcome " + fullname);
}
if(null != session.getAttribute("flashMessage")){   
	String strExpired = (String) session.getAttribute("flashMessage");
	if (strExpired.equals("notregistered")){     
		response.sendRedirect("RegisterPage.jsp");
	} 
	if (strExpired.equals("registered")){     
		out.print("<p>registration successfull.</p>");
		// remove flash message
		session.removeAttribute("flashMessage");
	} 
}      
%>

<h2><a href="logout">Logout</a></h2>
<h2><a href="addFootballer.jsp">add/remove player</a></h2>
<h2><a href="addTeam.jsp">add/remove team</a></h2>
<h2><a href="addLeague.jsp">add/remove league</a></h2>
<h2><a href="selectTeam.jsp">select team to manage</a></h2>

</body>
</html>