<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
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
	session.setAttribute("url","selectTeam.jsp");
	response.sendRedirect("LoginPage.jsp");
} else {
	if(null != session.getAttribute("name")){
		String fullname = (String) session.getAttribute("name");
		out.print("welcome " + fullname);
	}
	if(null != session.getAttribute("flashMessage")){   
		String strFlash = (String) session.getAttribute("flashMessage");
	}
	if(null == session.getAttribute("teamID") || 0 == (Integer) session.getAttribute("teamID")){ 
		System.out.print("no team id");
		session.setAttribute("flashMessage","mustSelectTeam");
		response.sendRedirect("selectTeam.jsp");
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
	Map<String, String > goalieMap = footballers.filterPlayers(teamID,"goalie");
	Map<String, String > defenseMap = footballers.filterPlayers(teamID,"defense");
	Map<String, String > midMap = footballers.filterPlayers(teamID,"mid");
	Map<String, String > attackMap = footballers.filterPlayers(teamID,"attack");
	String[] allSelectedPlayers = selectedTeam.getAllPlayers();
%>

<h2>Working With Team: <% out.print((teamName));%></h2>
<h3>Formation: defense-<% out.print((defense));%>, midfield-<% out.print((mid));%>, attack-<% out.print((attack));%></h3>
<h3><a href="selectTeam.jsp">Select new team and/or formation</a></h3>
<hr>

	<table border="1">
	
<!-- 
*************************************************GOALIE ROW***********************************
 -->
		<tr><th>Goal Keeper</th>
			<td> Goalie rating = goals + speed + strength</td>
			<%
			    Map<String, String> chosenGoalie = selectedTeam.getGoalie();
			    if (chosenGoalie.size() == 1){ %>
					<td>
						<% out.print(chosenGoalie.get("one")); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove goalie"/>
							<input type="hidden" name="position" value="one"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add goalie"/>
							<input type="hidden" name="position" value="one"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"goalie")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%} %>
		
		
		</tr>
		
<!-- 
*************************************************DEFENSE ROW***********************************
 -->
		<tr>
			<th>Defense</th><td> Defense rating = defense + speed + strength</td>
			<%
			    Map<String, String> chosenDefense = selectedTeam.getDefense();
			    String defensePositionOne = chosenDefense.get("one");
			    String defensePositionTwo = chosenDefense.get("two");
			    if (defensePositionOne != null){ %>
					<td>
						<% out.print(defensePositionOne); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove defense"/>
							<input type="hidden" name="position" value="one"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add defense"/>
							<input type="hidden" name="position" value="one"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"defense")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%} 
			    if (defensePositionTwo != null){ %>
					<td>
						<% out.print(defensePositionTwo); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove defense"/>
							<input type="hidden" name="position" value="two"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
					
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add defense"/>
							<input type="hidden" name="position" value="two"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"defense")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%}
		    	if (defense > 2) {
				    String defensePositionThree = chosenDefense.get("three");
				    if (defensePositionThree != null){ %>
						<td>
							<% out.print(defensePositionThree); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove defense"/>
								<input type="hidden" name="position" value="three"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add defense"/>
								<input type="hidden" name="position" value="three"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"defense")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}	    	
		    	}
		    	if (defense > 3) {
		    		String defensePositionFour = chosenDefense.get("four");
				    if (defensePositionFour != null){ %>
						<td>
							<% out.print(defensePositionFour); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove defense"/>
								<input type="hidden" name="position" value="four"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add defense"/>
								<input type="hidden" name="position" value="four"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"defense")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	}
		    	if (defense > 4) {
		    		String defensePositionFive = chosenDefense.get("five");
				    if (defensePositionFive != null){ %>
						<td>
							<% out.print(defensePositionFive); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove defense"/>
								<input type="hidden" name="position" value="five"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add defense"/>
								<input type="hidden" name="position" value="five"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"defense")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	} %>
			
		<tr/>
<!-- 
*************************************************MIDFIELD ROW***********************************
 -->
		<tr>
			<th>Midfield</th><td> Midfield rating = passing + speed + strength</td>
			<%
			    Map<String, String> chosenMid = selectedTeam.getMid();
			    String midPositionOne = chosenMid.get("one");
			    String midPositionTwo = chosenMid.get("two");
			    if (midPositionOne != null){ %>
					<td>
						<% out.print(midPositionOne); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove mid"/>
							<input type="hidden" name="position" value="one"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add mid"/>
							<input type="hidden" name="position" value="one"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"mid")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%} 
			    if (midPositionTwo != null){ %>
					<td>
						<% out.print(midPositionTwo); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove mid"/>
							<input type="hidden" name="position" value="two"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
					
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add mid"/>
							<input type="hidden" name="position" value="two"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"mid")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%}
		    	if (mid > 2) {
				    String midPositionThree = chosenMid.get("three");
				    if (midPositionThree != null){ %>
						<td>
							<% out.print(midPositionThree); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove mid"/>
								<input type="hidden" name="position" value="three"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add mid"/>
								<input type="hidden" name="position" value="three"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"mid")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}	    	
		    	}
		    	if (mid > 3) {
		    		String midPositionFour = chosenMid.get("four");
				    if (midPositionFour != null){ %>
						<td>
							<% out.print(midPositionFour); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove mid"/>
								<input type="hidden" name="position" value="four"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add mid"/>
								<input type="hidden" name="position" value="four"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"mid")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	}
		    	if (mid > 4) {
		    		String midPositionFive = chosenMid.get("five");
				    if (midPositionFive != null){ %>
						<td>
							<% out.print(midPositionFive); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove mid"/>
								<input type="hidden" name="position" value="five"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add mid"/>
								<input type="hidden" name="position" value="five"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"mid")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	} %>
			
		<tr/>
<!-- 
*************************************************ATTACK ROW***********************************
 -->
		<tr>
			<th>Attack</th><td> Attack rating = scoring + speed + strength</td>
			<%
			    Map<String, String> chosenAttack = selectedTeam.getAttack();
			    String attackPositionOne = chosenAttack.get("one");
			    String attackPositionTwo = chosenAttack.get("two");
			    if (attackPositionOne != null){ %>
					<td>
						<% out.print(attackPositionOne); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove attack"/>
							<input type="hidden" name="position" value="one"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add attack"/>
							<input type="hidden" name="position" value="one"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"attack")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%} 
			    if (attackPositionTwo != null){ %>
					<td>
						<% out.print(attackPositionTwo); %>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="remove attack"/>
							<input type="hidden" name="position" value="two"/>
							<input type="submit" value="remove"/>
						</form>
					</td>
					
			<%
				} else {%>
		    		<td>
						<form method=POST action=manageTeam.jsp>
							<input type="hidden" name="formName" value="add attack"/>
							<input type="hidden" name="position" value="two"/>
							<select name="footballer" onchange="this.form.submit()">		
							    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"attack")}'> 
							    	<c:set var="valid" value="true"/>  
							    	<% if (allSelectedPlayers.length >0){ 
							    		%>	    	
								    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
									    	<c:if test="${selected == entry.key}">	
									     		<c:set var="valid" value="false"/> 	
									     	</c:if>	
								     	</c:forEach>
							     	<% }%>
							     	<c:if test="${valid == true}">
								    	<option value="${entry.key}">${entry.value}</option>
								    </c:if>     	
							    </c:forEach>
							</select>
						</form>
					</td>		    	
		    	<%}
		    	if (attack > 2) {
				    String attackPositionThree = chosenAttack.get("three");
				    if (attackPositionThree != null){ %>
						<td>
							<% out.print(attackPositionThree); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove attack"/>
								<input type="hidden" name="position" value="three"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add attack"/>
								<input type="hidden" name="position" value="three"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"attack")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}	    	
		    	}
		    	if (attack > 3) {
		    		String attackPositionFour = chosenAttack.get("four");
				    if (attackPositionFour != null){ %>
						<td>
							<% out.print(attackPositionFour); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove attack"/>
								<input type="hidden" name="position" value="four"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add attack"/>
								<input type="hidden" name="position" value="four"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"attack")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	}
		    	if (attack > 4) {
		    		String attackPositionFive = chosenAttack.get("five");
				    if (attackPositionFive != null){ %>
						<td>
							<% out.print(attackPositionFive); %>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="remove attack"/>
								<input type="hidden" name="position" value="five"/>
								<input type="submit" value="remove"/>
							</form>
						</td>
					<%
					} else {%>
			    		<td>
							<form method=POST action=manageTeam.jsp>
								<input type="hidden" name="formName" value="add attack"/>
								<input type="hidden" name="position" value="five"/>
								<select name="footballer" onchange="this.form.submit()">		
								    <c:forEach var="entry" items='${footballers.filterPlayers(teamID,"attack")}'> 
								    	<c:set var="valid" value="true"/>  
								    	<% if (allSelectedPlayers.length >0){ 
								    		%>	    	
									    	<c:forEach var="selected" items="${selectedTeam.getAllPlayers()}"> 
										    	<c:if test="${selected == entry.key}">	
										     		<c:set var="valid" value="false"/> 	
										     	</c:if>	
									     	</c:forEach>
								     	<% }%>
								     	<c:if test="${valid == true}">
									    	<option value="${entry.key}">${entry.value}</option>
									    </c:if>     	
								    </c:forEach>
								</select>
							</form>
						</td>		    	
			    	<%}
		    	
		    	} %>
			
		<tr/>
	</table>
	
	
<!-- 
*************************************************PLAYER TABLE DIV***********************************
 -->
 <% if (allSelectedPlayers.length == 11){
 	session.setAttribute("completeTeam","yes");%>
	 	
	 	<h2><a href="CalculateTeam">Calculate Team Score</a></h2>
	 	
	<% } else{
 	session.setAttribute("completeTeam","no");%>
	 	
	<% }%>

 
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