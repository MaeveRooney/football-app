package formation;

import java.io.IOException;
import java.io.PrintWriter;
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
		int defense = 0;
		int mid = 0;
		int attack = 0;
		String teamName = "";
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
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
		// titles and info about team
	    out.println("<BODY>\n" +
	                "<H1>Select Players For Team</H1>" +
	                "<h2>Working With Team: "+teamName + "</h2>" +
	                "<h3>Formation: defense-"+defense+", midfield-"+mid+", attack-"+attack+"</h3>" +
	                "<h3><a href='selectTeam.jsp'>Select new team and/or formation</a></h3><hr>");
	    
	    ChangeFormation selectedTeam = new ChangeFormation();
	    if (request.getParameterMap().containsKey("formName")) {
			String formName = request.getParameter("formName");
			String position = request.getParameter("position");
			if (request.getParameterMap().containsKey("footballer")) {
				String footballer = request.getParameter("footballer");
				selectedTeam = new ChangeFormation(formName, position, footballer);
			} else {
				selectedTeam = new ChangeFormation(formName, position);
				
			}
		}    
	    ListFootballers footballers = new ListFootballers();
	    selectedTeam.processRequest();
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
			String removeButton = "<form method=POST action=SelectPlayers>" +
							"<input type='hidden' name='formName' value='remove goalie'/>" +
							"<input type='hidden' name='position' value='one'/>" +
							"<input type='submit' value='remove'/>"+
							"</form>";
			out.println("<td>"+goalie+removeButton+"</td>");
		} else {
			out.println("<td>" +
			"<form method=POST action=SelectPlayers>" +
				"<input type='hidden' name='formName' value='add goalie'/>" +
				"<input type='hidden' name='position' value='one'/>" +
				"<select name='footballer' onchange='this.form.submit()'>");
			for (Map.Entry<String, String> entry : goalieMap.entrySet()){
				String key = entry.getKey();
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
		//End Goalie Row
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
