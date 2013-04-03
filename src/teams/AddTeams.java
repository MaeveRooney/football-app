package teams;

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
@WebServlet("/addTeams")
public class AddTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeams() {
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
			System.out.println("In the add team Servlet");
			TeamBean team = new TeamBean();
			String name = request.getParameter("name");
			String league = request.getParameter("league");
			team.setName(name);
			team.setLeague(league);

			team = TeamDAO.register(team);
			if(team.isValid())
			{	
				session.setAttribute("flashMessage","teamadded");
				session.setAttribute("teamName",name);
				response.sendRedirect("addTeam.jsp");
			}else{
				session.setAttribute("flashMessage","teamfail");
				response.sendRedirect("addTeam.jsp");
			}
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}
	

}
