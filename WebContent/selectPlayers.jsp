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

<jsp:useBean id="cart" scope="session" class="formation.changeFormation" />

<jsp:setProperty name="cart" property="*" />
<%
    cart.processRequest();
%>

<%	if (cart.getFootballers().length >0){ %>

<br> <h2>Selected Defense:</h2>

<ol>
<%
    String[] footballers = cart.getFootballers();
    for (int i=0; i<footballers.length; i++) {
%>
<li> <% out.print((footballers[i])); %>
<%
    	}
    }
%>
</ol>


<hr>

<form method=POST action=selectPlayers.jsp>
<BR>
Please select player to add to defense:
<br>

<jsp:useBean id="obj2" class="footballers.ListFootballers" scope="session"/>
<select name="footballer">
	<% if (cart.getFootballers().length >0){ %>
	    <c:forEach var="entry" items="${obj2.items}"> 
	    	<c:set var="valid" value="true"/>  	    	
	    	<c:forEach var="selected" items="${cart.getFootballers()}"> 
		    	<c:if test="${selected == entry.value}">	
		     		<c:set var="valid" value="false"/> 	
		     	</c:if>	
	     	</c:forEach>
	     	<c:if test="${valid == true}">
		    	<option value="${entry.value}">${entry.value}</option>
		    </c:if>     	
	    </c:forEach>
    <% }if (cart.getFootballers().length == 0){ %>
     	<c:forEach var="entry" items="${obj2.items}">
     		<option value="${entry.value}">${entry.value}</option>
     	</c:forEach>
     <%} %>
</select>


<br> <br>
<INPUT TYPE=submit name="submit" value="add">
</form>


<% if (cart.getFootballers().length >0){ %>
<form method=POST action=selectPlayers.jsp>
<BR>
Please select player to remove from defense:
<br>
<select name="footballer">	    	
   	<c:forEach var="selected" items="${cart.getFootballers()}"> 
     		<option value="${selected}">${selected}</option>	
   	</c:forEach>   	
</select>
<br> <br>
<INPUT TYPE=submit name="submit" value="remove">
</form>
<%} %>

</body>
</html>