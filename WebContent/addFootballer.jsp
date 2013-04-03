<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Football Manager - add footballer</title>
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
	if (strExpired.equals("playerfail")){ 		
		out.print("<p>adding player was not successful</p>");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("playeradded")){ 
		String playerName = (String) session.getAttribute("playerName");
		out.print("<p>You sucessfully added " + playerName + "</p>");
		// remove flash message		
		session.removeAttribute("playerName");
		session.removeAttribute("flashMessage");
	}
	if (strExpired.equals("playerremoved")){ 		
		out.print("<p>player was sucessfully removed</p>");
		session.removeAttribute("flashMessage");
	} 
	if (strExpired.equals("playerremovefail")){ 
		out.print("<p>The player was not removed. Please try again</p>");
		// remove flash message		
		session.removeAttribute("flashMessage");
	}
}
%>
<h1>Add Footballer</h1>
<form name="actionForm" action="addFootballers" method ="POST">
<input type="hidden" name="form" value="add"/>
<table>
<tr><td>Enter players Full Name: </td><td><input type="text" name="fullName"/></td><td></td></tr>
<tr><td>Enter players Shirt Number: </td><td><input type="text" name="shirtNumber"/></td><td>number between 0 and 999</td></tr>
<tr><td>Enter players speed rating: </td><td><select name="speed">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>
<tr><td>Enter players strength rating: </td><td><select name="strength">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>											
<tr><td>Enter players passing rating: </td><td><select name="passing">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>
<tr><td>Enter players scoring ability rating: </td><td><select name="scoring">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>
<tr><td>Enter players defense rating: </td><td><select name="defense">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>
<tr><td>Enter players goal keeping rating: </td><td><select name="goals">
													<c:forEach begin="0" end="10" var="i">
													   <option value="${i}">${i}</option>
													</c:forEach>
											</select></td><td></td></tr>	
<tr><td>Add player to team (optional): </td>
<td>
	<jsp:useBean id="obj" class="teams.ListTeams" scope="page"/>
	<select name="team">
	    <c:forEach var="entry" items="${obj.items}">
	     <option value="${entry.key}">${entry.value}</option>
	    </c:forEach>
	</select></td></tr>																																
<tr><td colspan="2" align="center"><input type="submit" value="Add Player"> </td></tr>
</table>
</form>

<h1>Remove Footballer</h1>
<form name="actionForm" action="addFootballers" method ="POST">
<input type="hidden" name="form" value="remove"/>
<table>	
<!--  
	<tr><td>Filter by team: </td>
	<td>
		<select name="team">
		    <c:forEach var="entry" items="${obj.items}">
		     <option value="${entry.key}">${entry.value}</option>
		    </c:forEach>
		</select></td><td>If no team selected all players not on any team are listed.</tr>	
-->
<tr><td>Remove Footballer: </td>
<td>
	<jsp:useBean id="obj2" class="footballers.ListFootballers" scope="page"/>
	<div id="playerList">
	<select name="footballer">
	    <c:forEach var="entry" items="${obj2.items}">
	     	<option value="${entry.key}">${entry.value}</option>
	    </c:forEach>
	</select>
	</div></td></tr>																																
<tr><td colspan="2" align="center"><input type="submit" value="Remove Player"> </td></tr>
</table>
</form>
<h2><a href="addTeam.jsp">add team</a></h2>

</body>
</html>