<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Football Manager - add team</title>
</head>
<body>
<%
if(null == session.getAttribute("user")){   
	session.setAttribute("flashMessage","mustlogin");
	session.setAttribute("url","addLeague.jsp");
	response.sendRedirect("LoginPage.jsp");
} else if(null != session.getAttribute("name")){
	String fullname = (String) session.getAttribute("name");
	out.print("<p>You are logged in as " + fullname + "</p>");
}
if(null != session.getAttribute("flashMessage")){   
	String strExpired = (String) session.getAttribute("flashMessage");
	if (strExpired.equals("leaguefail")){ 		
		out.print("<p>adding league was not successful</p>");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("leagueadded")){ 
		String leagueName = (String) session.getAttribute("leagueName");
		out.print("<p>You sucessfully added " + leagueName + "</p>");
		// remove flash message		
		session.removeAttribute("leagueName");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("leagueremovefail")){ 		
		out.print("<p>league was not removed</p>");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("leagueremoved")){ 
		String leagueName = (String) session.getAttribute("leagueName");
		out.print("<p>You sucessfully removed " + leagueName + "</p>");
		// remove flash message		
		session.removeAttribute("leagueName");
		session.removeAttribute("flashMessage");
	}
}
%>
<h1>Add League</h1>
<form name="actionForm" action="addLeagues" method ="POST">
<input type="hidden" name="form" value="add"/>
<table>
<tr><td>Enter League Name: </td><td><input type="text" name="name"/></td><td></td></tr>
																															
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>
<h1>Remove League</h1>
<p> Warning: If you remove a league that has teams in it, these teams will be automatically removed from it and will need to be placed in a new league as needed</p>
<form name="actionForm" action="addLeagues" method ="POST">
<input type="hidden" name="form" value="remove"/>
<table>
<tr><td>Select League to remove: </td><td><jsp:useBean id="obj" class="teams.ListLeagues" scope="page"/>
	<select name="name">
	    <c:forEach var="item" items="${obj.items}">
	     <option value="${item}">${item}</option>
	    </c:forEach>
	</select></td></tr>
																															
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>
<h2><a href="addFootballer.jsp">add footballer</a></h2>
<h2><a href="addTeam.jsp">add team</a></h2>

</body>
</html>