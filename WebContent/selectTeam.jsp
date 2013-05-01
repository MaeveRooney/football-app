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
if(null!= session.getAttribute("completeTeam")){
	session.removeAttribute("completeTeam");
	session.removeAttribute("selectedTeam");
}
if(null == session.getAttribute("user")){   
	session.setAttribute("flashMessage","mustlogin");
	session.setAttribute("url","selectTeam.jsp");
	response.sendRedirect("LoginPage.jsp");
} else if(null != session.getAttribute("name")){
	String fullname = (String) session.getAttribute("name");
	out.print("<p>You are logged in as " + fullname + "</p>");
}
if(null != session.getAttribute("flashMessage")){   
	String strFlash = (String) session.getAttribute("flashMessage");
	if (strFlash.equals("mustSelectTeam")){
		out.print("<h2>You must select a team and formation to try out team formations</h2>");
	}
}
%>
<h2>Choose Team</h2>
<form name="actionForm" action="ChooseTeam" method ="POST">
<table>
<tr><td>Select Team: </td>
<td>
	<jsp:useBean id="obj" class="teams.ListTeams" scope="page"/>
	<select name="teamID">
	    <c:forEach var="entry" items="${obj.items}">
	     <option value="${entry.key}">${entry.value}</option>
	    </c:forEach>
	</select></td></tr>	
																														
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>

<h2><a href="addFootballer.jsp">add footballer</a></h2>
<h2><a href="addTeam.jsp">add team</a></h2>
<h2><a href="addLeague.jsp">add League</a></h2>

</body>
</html>