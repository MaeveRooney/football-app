<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Football Manager - select Players</title>
</head>
<body>
<%
int teamID = 0;
int defense = 0;
int mid = 0;
int attack = 0;
String teamName = "";
if(null == session.getAttribute("user")){   
	session.setAttribute("flashMessage","mustlogin");
	session.setAttribute("url","selectFormation.jsp");
	response.sendRedirect("LoginPage.jsp");
} else {
	if(null != session.getAttribute("name")){
		String fullname = (String) session.getAttribute("name");
		out.print("welcome " + fullname);
	}
	if(null != session.getAttribute("flashMessage")){   
		String strFlash = (String) session.getAttribute("flashMessage");
	}
	if(null == session.getAttribute("teamID")){ 
		System.out.print("no team id");
		session.setAttribute("flashMessage","mustSelectTeam");
		response.sendRedirect("selectFormation.jsp");
	} else {
		teamID = (Integer) session.getAttribute("teamID");
		System.out.print("team id " + teamID);
	}
	if(null != session.getAttribute("teamName")){ 
		teamName = (String) session.getAttribute("teamName");
		System.out.print("team Name is " + teamName);
	}
	if(null != session.getAttribute("defense")){ 
		defense = (Integer) session.getAttribute("defense");
		System.out.print("defense int is " + defense);
	}
	if(null != session.getAttribute("mid")){ 
		mid = (Integer) session.getAttribute("mid");
		System.out.print("mid int is " + mid);
	} 
	if(null != session.getAttribute("attack")){ 
		attack = (Integer) session.getAttribute("attack");
		System.out.print("attack int is " + attack);
	} 
}
%>

<jsp:useBean id="footballers" class="footballers.ListFootballers" scope="session"/>
<jsp:useBean id="selectedTeam" scope="session" class="formation.ChangeFormation" />

<jsp:setProperty name="selectedTeam" property="*" />
<%
    selectedTeam.processRequest();
%>

<h2>Working With Team: <% out.print((teamName));%></h2>
<h3>Formation: defense-<% out.print((defense));%>, midfield-<% out.print((mid));%>, attack-<% out.print((attack));%></h3>
<h3><a href="selectFormation.jsp">Select new team and/or formation</a></h3>
<hr>
<!-- 
*************************************************GOALIE DIV***********************************
 -->


<!-- 
*************************************************PLAYER TABLE DIV***********************************
 -->
<div style="float: left; width: 100%;">	<!-- player table div -->
	<% if (footballers.getPlayerInfoForTeam(teamID).size() >0){ %>
	<h2>All Players On Team</h2>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>ShirtNumber</th>
			<th>Speed Rating</th>
			<th>Strength Rating</th>
			<th>Scoring Ability Rating</th>
			<th>Passing Rating</th>
			<th>Defense Rating</th>
			<th>Goal Keeping Rating</th>
		</tr>
		<c:forEach var="footballer" items="${footballers.getPlayerInfoForTeam(teamID)}"> 
	     	<tr>
	     		<td>${footballer['fullName']}</td>
	     		<td>${footballer['shirtNumber']}</td>	
	     		<td>${footballer['speed']}</td>	
	     		<td>${footballer['strength']}</td>	
	     		<td>${footballer['scoring']}</td>	
	     		<td>${footballer['passing']}</td>	
	     		<td>${footballer['defense']}</td>	
	     		<td>${footballer['goals']}</td>	
	     	</tr>	
	   	</c:forEach>  
	</table>
	
	<%} else{%>
	<h2>No players on team. Please add some so you can work on formations</h2>
	<%}%>
</div><!-- closing player table div -->
</body>
</html>