package formation;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import footballers.ListFootballers;

/**
 * Servlet implementation class SelectPlayers
 */
@WebServlet("/SelectPlayers")
public class SelectPlayers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPlayers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
		int teamID = 0;
		int defenseNumber = 0;
		int midNumber = 0;
		int attackNumber = 0;
		String teamName = "";
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    ChangeFormation selectedTeam;
	    if (null == session.getAttribute("selectedTeam")){
	    	selectedTeam = new ChangeFormation();
	    	session.setAttribute("selectedTeam", selectedTeam);
	    } else {
	    	selectedTeam = (ChangeFormation) session.getAttribute("selectedTeam");
	    }
		if(null == session.getAttribute("user")){   
			session.setAttribute("flashMessage","mustlogin");
			session.setAttribute("url","selectTeam.jsp");
			response.sendRedirect("LoginPage.jsp");
			return;
		} else {
			if(null != session.getAttribute("name")){
				String fullname = (String) session.getAttribute("name");
				out.print("welcome " + fullname);
			}
			if(null == session.getAttribute("teamID") || 0 == (Integer) session.getAttribute("teamID")){ 
				System.out.print("no team id");
				session.setAttribute("flashMessage","mustSelectTeam");
				response.sendRedirect("selectTeam.jsp");
				return;
			} else {
				teamID = (Integer) session.getAttribute("teamID");
				System.out.print("team id " + teamID);
			}
			if(null != session.getAttribute("teamName")){ 
				teamName = (String) session.getAttribute("teamName");
				System.out.print("team Name is " + teamName);
			}
			if(null != session.getAttribute("defense")){ 
				defenseNumber = (Integer) session.getAttribute("defense");
				System.out.print("defense int is " + defenseNumber);
			}
			if(null != session.getAttribute("mid")){ 
				midNumber = (Integer) session.getAttribute("mid");
				System.out.print("mid int is " + midNumber);
			} 
			if(null != session.getAttribute("attack")){ 
				attackNumber = (Integer) session.getAttribute("attack");
				System.out.print("attack int is " + attackNumber);
			} 
		}
		// titles and info about team
	    out.println("<BODY>\n" +
	                "<H1>Select Players For Team</H1>" +
	                "<h2>Working With Team: "+teamName + "</h2>" +
	                "<h3>Formation: defense-"+defenseNumber+", midfield-"+midNumber+", attack-"+attackNumber+"</h3>" +
	                "<h3><a href='selectTeam.jsp'>Select new team and/or formation</a></h3><hr>");

	    if (request.getParameterMap().containsKey("formName")) {
			String formName = request.getParameter("formName");
			String position = request.getParameter("position");
			if (request.getParameterMap().containsKey("footballer")) {
				String footballer = request.getParameter("footballer");
				footballer = URLDecoder.decode(footballer, "UTF-8");
				selectedTeam.addFootballer(formName, position, footballer);
			} else {
				selectedTeam.removeFootballer(formName, position);				
			}
		}
	    	    
	    ListFootballers footballers = new ListFootballers();	    
		Map<String, String > goalieMap = footballers.filterPlayers(teamID,"goalie");
		Map<String, String > defenseMap = footballers.filterPlayers(teamID,"defense");
		Map<String, String > midMap = footballers.filterPlayers(teamID,"mid");
		Map<String, String > attackMap = footballers.filterPlayers(teamID,"attack");
		String[] allSelectedPlayers = selectedTeam.getAllPlayers();
		
		//Table to select players
		out.println("<table border='1'>");
		
		//Goalie Row
		out.println("<tr><th>Goal Keeper</th>" +
					"<td>Goalie Rating = goals + strength + speed</td>");
		
		Map<String, String> chosenGoalie = selectedTeam.getGoalie();
		if (chosenGoalie.size() == 1){
			String goalie = chosenGoalie.get("one");
			String removeButton = "<form method=POST id='removegoalie' action=SelectPlayers>" +
							"<input type='hidden' name='formName' value='remove goalie'/>" +
							"<input type='hidden' name='position' value='one'/>" +
							"<input type='submit' value='remove'/>"+
							"</form>";
			out.println("<td style='width:200px;'>"+goalie+removeButton+"</td>");
		} else {
			out.println("<td style='width:200px;'>" +
			"<form method=POST id='addgoalie' action=SelectPlayers>" +
				"<input type='hidden' name='formName' value='add goalie'/>" +
				"<input type='hidden' name='position' value='one'/>" +
				"<select name='footballer' onchange='this.form.submit()'>");
			for (Map.Entry<String, String> entry : goalieMap.entrySet()){
				String key = URLEncoder.encode(entry.getKey(), "UTF-8");
				String value = entry.getValue();
				boolean valid = true;
				if (allSelectedPlayers.length >0){
					for (int i = 0; i < allSelectedPlayers.length; i++){
						String player = allSelectedPlayers[i];
						if (allSelectedPlayers[i].matches(key)){
							valid = false;
						}
					}
				}
				if (valid){
					out.println("<option value="+key+">"+value+"</option>");
				}
			}				   
			out.println("</form></td>");
		}
		//End Goalie Row
		out.println("</tr>");
		
		//Start Defense Row
		out.println("<tr><th>Defense</th>" +
							"<td>Defense Rating = defense + strength + speed</td>");
				
		Map<String, String> chosenDefense = selectedTeam.getDefense();
		String defensePositionOne = chosenDefense.get("one");
	    String defensePositionTwo = chosenDefense.get("two");
		if (defensePositionOne != null){
			String removeButton = "<form method=POST id='removedefense1' action=SelectPlayers>" +
							"<input type='hidden' name='formName' value='remove defense'/>" +
							"<input type='hidden' name='position' value='one'/>" +
							"<input type='submit' value='remove'/>"+
							"</form>";
			out.println("<td style='width:200px;'>"+defensePositionOne+removeButton+"</td>");
		} else {
			out.println("<td style='width:200px;'>" +
			"<form method=POST id='adddefense1' action=SelectPlayers>" +
				"<input type='hidden' name='formName' value='add defense'/>" +
				"<input type='hidden' name='position' value='one'/>" +
				"<select name='footballer' onchange='this.form.submit()'>");
			for (Map.Entry<String, String> entry : defenseMap.entrySet()){
				String key = URLEncoder.encode(entry.getKey(), "UTF-8");
				String value = entry.getValue();
				boolean valid = true;
				if (allSelectedPlayers.length >0){
					for (int i = 0; i < allSelectedPlayers.length; i++){					
						if (allSelectedPlayers[i].matches(key)){
							valid = false;
						}
					}
				}
				if (valid){
					out.println("<option value="+key+">"+value+"</option>");
				}
			}				   
			out.println("</form></td>");
		}
		 
		if (defensePositionTwo != null){
			String removeButton = "<form method=POST id='removedefense2' action=SelectPlayers>" +
							"<input type='hidden' name='formName' value='remove defense'/>" +
							"<input type='hidden' name='position' value='two'/>" +
							"<input type='submit' value='remove'/>"+
							"</form>";
			out.println("<td style='width:200px;'>"+defensePositionTwo+removeButton+"</td>");
		} else {
			out.println("<td style='width:200px;'>" +
			"<form method=POST id='adddefense2' action=SelectPlayers>" +
				"<input type='hidden' name='formName' value='add defense'/>" +
				"<input type='hidden' name='position' value='two'/>" +
				"<select name='footballer' onchange='this.form.submit()'>");
			for (Map.Entry<String, String> entry : defenseMap.entrySet()){
				String key = URLEncoder.encode(entry.getKey(), "UTF-8");
				String value = entry.getValue();
				boolean valid = true;
				if (allSelectedPlayers.length >0){
					for (int i = 0; i < allSelectedPlayers.length; i++){					
						if (allSelectedPlayers[i].matches(key)){
							valid = false;
						}
					}
				}
				if (valid){
					out.println("<option value="+key+">"+value+"</option>");
				}
			}				   
			out.println("</form></td>");
		} 
		
		if (defenseNumber > 2) {
			String positionThree = chosenDefense.get("three");
			if (positionThree != null){
				String removeButton = "<form method=POST id='removedefense3' action=SelectPlayers>" +
								"<input type='hidden' name='formName' value='remove defense'/>" +
								"<input type='hidden' name='position' value='three'/>" +
								"<input type='submit' value='remove'/>"+
								"</form>";
				out.println("<td style='width:200px;'>"+positionThree+removeButton+"</td>");
			} else {
				out.println("<td style='width:200px;'>" +
				"<form method=POST id='adddefense3' action=SelectPlayers>" +
					"<input type='hidden' name='formName' value='add defense'/>" +
					"<input type='hidden' name='position' value='three'/>" +
					"<select name='footballer' onchange='this.form.submit()'>");
				for (Map.Entry<String, String> entry : defenseMap.entrySet()){
					String key = URLEncoder.encode(entry.getKey(), "UTF-8");
					String value = entry.getValue();
					boolean valid = true;
					if (allSelectedPlayers.length >0){
						for (int i = 0; i < allSelectedPlayers.length; i++){					
							if (allSelectedPlayers[i].matches(key)){
								valid = false;
							}
						}
					}
					if (valid){
						out.println("<option value="+key+">"+value+"</option>");
					}
				}				   
				out.println("</form></td>");			
			}
		}
		
		if (defenseNumber > 3) {
			String positionFour = chosenDefense.get("four");
			if (positionFour != null){
				String removeButton = "<form method=POST id='removedefense4' action=SelectPlayers>" +
								"<input type='hidden' name='formName' value='remove defense'/>" +
								"<input type='hidden' name='position' value='four'/>" +
								"<input type='submit' value='remove'/>"+
								"</form>";
				out.println("<td style='width:200px;'>"+positionFour+removeButton+"</td>");
			} else {
				out.println("<td style='width:200px;'>" +
				"<form method=POST id='adddefense4' action=SelectPlayers>" +
					"<input type='hidden' name='formName' value='add defense'/>" +
					"<input type='hidden' name='position' value='four'/>" +
					"<select name='footballer' onchange='this.form.submit()'>");
				for (Map.Entry<String, String> entry : defenseMap.entrySet()){
					String key = URLEncoder.encode(entry.getKey(), "UTF-8");
					String value = entry.getValue();
					boolean valid = true;
					if (allSelectedPlayers.length >0){
						for (int i = 0; i < allSelectedPlayers.length; i++){					
							if (allSelectedPlayers[i].matches(key)){
								valid = false;
							}
						}
					}
					if (valid){
						out.println("<option value="+key+">"+value+"</option>");
					}
				}				   
				out.println("</form></td>");
			}
		} 
		
		if (defenseNumber > 4) {
			String positionFive = chosenDefense.get("five");
			if (positionFive != null){
				String removeButton = "<form method=POST id='removedefense5' action=SelectPlayers>" +
								"<input type='hidden' name='formName' value='remove defense'/>" +
								"<input type='hidden' name='position' value='five'/>" +
								"<input type='submit' value='remove'/>"+
								"</form>";
				out.println("<td style='width:200px;'>"+positionFive+removeButton+"</td>");
			} else {
				out.println("<td style='width:200px;'>" +
				"<form method=POST id='adddefense5' action=SelectPlayers>" +
					"<input type='hidden' name='formName' value='add defense'/>" +
					"<input type='hidden' name='position' value='five'/>" +
					"<select name='footballer' onchange='this.form.submit()'>");
				for (Map.Entry<String, String> entry : defenseMap.entrySet()){
					String key = URLEncoder.encode(entry.getKey(), "UTF-8");
					String value = entry.getValue();
					boolean valid = true;
					if (allSelectedPlayers.length >0){
						for (int i = 0; i < allSelectedPlayers.length; i++){					
							if (allSelectedPlayers[i].matches(key)){
								valid = false;
							}
						}
					}
					if (valid){
						out.println("<option value="+key+">"+value+"</option>");
					}
				}				   
				out.println("</form></td>");
			}
		}
		//End defense Row
		out.println("</tr>");
		
		
		// List All players on team and their traits
		if (footballers.getPlayerInfoForTeam(teamID).size() >0){ 
			out.println("<h2>All Players On Team</h2>" +
						"<table border='1'>" +
							"<tr>" +
								"<th>Name</th>" +
								"<th>ShirtNumber</th>" +
								"<th>Speed Rating</th>" +
								"<th>Strength Rating</th>" +
								"<th>Scoring Ability Rating</th>" +
								"<th>Passing Rating</th>" +
								"<th>Defense Rating</th>" +
								"<th>Goal Keeping Rating</th>" +
							"</tr>");
			for (int i=0; i< footballers.getPlayerInfoForTeam(teamID).size(); i++ ){
				HashMap<String,String> map = footballers.getPlayerInfoForTeam(teamID).get(i);
				out.println("<tr>" +
					     		"<td>"+map.get("fullName")+"</td>" +
					     		"<td>"+map.get("shirtNumber")+"</td>" +	
					     		"<td>"+map.get("speed")+"</td>" +	
					     		"<td>"+map.get("strength")+"</td>" +	
					     		"<td>"+map.get("scoring")+"</td>" +	
					     		"<td>"+map.get("passing")+"</td>" +	
					     		"<td>"+map.get("defense")+"</td>" +	
					     		"<td>"+map.get("goals")+"</td>" +	
					     	"</tr>");	
			}
			out.println("</table>");
		} else {
			out.println("<h2>No players on team. Please add some so you can work on formations</h2>");
		}
		session.setAttribute("selectedTeam", selectedTeam);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
