package footballers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class addFootballers
 */
@WebServlet("/addFootballers")
public class AddFootballers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFootballers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get current session, or initialize one if none
		HttpSession session = request.getSession(true);
		
		try
		{
			System.out.println("In the add footballer Servlet");
			PlayerBean player = new PlayerBean();
			String fullName = request.getParameter("fullName");
			String shirtNumber = request.getParameter("shirtNumber");
			String speed = request.getParameter("speed");
			String strength = request.getParameter("strength");
			String passing = request.getParameter("passing");
			String scoring = request.getParameter("scoring");
			String defense = request.getParameter("defense");
			String goals = request.getParameter("goals");
			String team = request.getParameter("team");			
			player.setFullName(fullName);
			player.setShirtNumber(shirtNumber);
			player.setSpeed(speed);
			player.setStrength(strength);
			player.setPassing(passing);
			player.setScoring(scoring);
			player.setDefense(defense);
			player.setGoals(goals);
			player = FootballerDAO.register(player);
			if(player.isValid())
			{	
				int playerID;
				playerID = player.getID();
				if ((team.length() > 0) && !(team.equals("no teams in database. please add some teams"))){
					FootballerDAO.addToTeam(playerID, team);
				}
				session.setAttribute("flashMessage","playeradded");
				session.setAttribute("playerName",player.getFullName());
				response.sendRedirect("addFootballer.jsp");
			}else{
				session.setAttribute("flashMessage","playerfail");
				response.sendRedirect("addFootballer.jsp");
			}
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}
	

}
