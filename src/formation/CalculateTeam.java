package formation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import footballers.ListFootballers;

/**
 * Servlet implementation class CalculateTeam
 */
@WebServlet("/CalculateTeam")
public class CalculateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    if(null == session.getAttribute("user")){   
			session.setAttribute("flashMessage","mustlogin");
			session.setAttribute("url","selectTeam.jsp");
			response.sendRedirect("LoginPage.jsp");
			return;
		}
	    if(null == session.getAttribute("completeTeam")){  
			response.sendRedirect("manageTeam.jsp");
			return;
		}
	    int teamID = (Integer) session.getAttribute("teamID");
	    ChangeFormation selectedTeam = (ChangeFormation) session.getAttribute("selectedTeam");
	    Map<String, String>	selectedGoalie = selectedTeam.getGoalie();
	    Map<String, String>	selectedDefense = selectedTeam.getDefense();
	    Map<String, String>	selectedMid = selectedTeam.getMid();
	    Map<String, String>	selectedAttack = selectedTeam.getAttack();
	    ListFootballers listPlayers = new ListFootballers();
	    out.println("<h1>Selected Team</h1>" +
	    			"<table border='1'><tr><th>Goalie</th>");
	    int goalieScore = 0;
	    for (Map.Entry<String, String> entry : selectedGoalie.entrySet()){
	    	String value = entry.getValue();
			int score = listPlayers.GetGoalieScore(teamID,value);
			goalieScore = score;
			out.println("<td>"+value+"<br>score: "+score+"/30</td>");
	    }
	    out.println("</tr><tr><th>Defense</th>");
	    int defenseScore = 0;
	    int defenseOutOf = 0;
	    for (Map.Entry<String, String> entry : selectedDefense.entrySet()){
	    	String value = entry.getValue();
			int score = listPlayers.GetDefenseScore(teamID,value);
			defenseScore+=score;
			defenseOutOf+=30;
			out.println("<td>"+value+"<br>score: "+score+"/30</td>");
	    }
	    out.println("</tr><tr><th>MidField</th>");
	    int midScore = 0;
	    int midOutOf = 0;
	    for (Map.Entry<String, String> entry : selectedMid.entrySet()){
	    	String value = entry.getValue();
			int score = listPlayers.GetMidScore(teamID,value);
			midScore+=score;
			midOutOf+=30;
			out.println("<td>"+value+"<br>score: "+score+"/30</td>");
	    }
	    out.println("</tr><tr><th>Attack</th>");
	    int attackScore = 0;
	    int attackOutOf = 0;
	    for (Map.Entry<String, String> entry : selectedAttack.entrySet()){
	    	String value = entry.getValue();
			int score = listPlayers.GetAttackScore(teamID,value);
			attackScore+=score;
			attackOutOf+=30;
			out.println("<td>"+value+"<br>score: "+score+"/30</td>");
	    }
	    int totalScore = attackScore + midScore + defenseScore + goalieScore;
	    out.println("</tr></table><br><br>" +
	    			"<h4>Goalie Score:</h4><p> "+goalieScore+"/30</p>" +
	    			"<h4>Defense Score:</h4><p> "+defenseScore+"/"+defenseOutOf+"</p>"+
	    			"<h4>Midfield Score:</h4><p> "+midScore+"/"+midOutOf+"</p>" +
	   				"<h4>Attack Score:</h4><p> "+attackScore+"/"+attackOutOf+"</p>"+
	    			"<h2>Total Score:</h2><h4> "+totalScore+"/330</h4><br><br>"+
	   				"<h4>To select another Team/Formation <a href='selectTeam.jsp'>click here</a></h4>");
	    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
