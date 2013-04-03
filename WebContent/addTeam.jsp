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
	response.sendRedirect("LoginPage.jsp");
} else if(null != session.getAttribute("name")){
	String fullname = (String) session.getAttribute("name");
	out.print("<p>You are logged in as " + fullname + "</p>");
}
if(null != session.getAttribute("flashMessage")){   
	String strExpired = (String) session.getAttribute("flashMessage");
	if (strExpired.equals("teamfail")){ 		
		out.print("<p>adding team was not successful</p>");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("teamadded")){ 
		String teamName = (String) session.getAttribute("teamName");
		out.print("<p>You sucessfully added " + teamName + "</p>");
		// remove flash message		
		session.removeAttribute("teamName");
		session.removeAttribute("flashMessage");
	} 
}
%>
<form name="actionForm" action="addTeams" method ="POST">
<table>
<tr><td>Enter Team Name: </td><td><input type="text" name="name"/></td><td></td></tr>
<tr><td>Add Team To League: </td>
<td>
	<jsp:useBean id="obj" class="teams.ListLeagues" scope="page"/>
	<select name="league">
	    <c:forEach var="item" items="${obj.items}">
	     <option value="${item}">${item}</option>
	    </c:forEach>
	</select></td></tr>																																
<tr><td colspan="2" align="center"><input type="submit" value="submit"> </td></tr>
</table>
</form>

<h2><a href="addFootballer.jsp">add footballer</a></h2>

</body>
</html>