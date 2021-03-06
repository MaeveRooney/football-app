<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Football Manager - Select Team And Formation</title>
</head>
<body>
<c:remove var="selectedTeam" scope="session"/>
<%
if(null == session.getAttribute("user")){   
	session.setAttribute("flashMessage","mustlogin");
	session.setAttribute("url","selectTeam.jsp");
	response.sendRedirect("LoginPage.jsp");
} else if(null != session.getAttribute("name")){
	String fullname = (String) session.getAttribute("name");
	out.print("<p>You are logged in as " + fullname + "</p>");
}


%>
<h3>Team: <% out.print(session.getAttribute("teamName")); %></h3>
<h2>Choose Formation</h2>
<form name="actionForm" action="ChooseFormation" method ="POST">
<table>
<tr><td>Select Formation: </td>
<td>
	<select name="formation">
	     <option value="3,4,3">3,4,3</option>
	     <option value="4,3,3">4,3,3</option>
	     <option value="3,3,4">3,3,4</option>
	     <option value="4,4,2">4,4,2</option>
	     <option value="2,4,4">2,4,4</option>
	     <option value="4,2,4">4,2,4</option>
	     <option value="5,3,2">5,3,2</option>
	     <option value="5,2,3">5,2,3</option>
	     <option value="2,5,3">2,5,3</option>
	     <option value="3,5,3">3,5,2</option>
	     <option value="2,3,5">2,3,5</option>
	     <option value="3,2,5">3,2,5</option>
	</select></td><td>Defense, Midfield, Attack</td></tr>																															
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>

<h2><a href="addFootballer.jsp">add footballer</a></h2>
<h2><a href="addTeam.jsp">add team</a></h2>
<h2><a href="addLeague.jsp">add League</a></h2>

</body>
</html>