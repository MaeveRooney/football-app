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
@WebServlet("/addLeagues")
public class AddLeagues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLeagues() {
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
			System.out.println("In the add league Servlet");
			LeagueBean league = new LeagueBean();
			String name = request.getParameter("name");
			league.setName(name);
			if (request.getParameter("form").equals("add")){
				league = LeagueDAO.register(league);
				if(league.isValid())
				{	
					session.setAttribute("flashMessage","leagueadded");
					session.setAttribute("leagueName",name);
					response.sendRedirect("addLeague.jsp");
				}else{
					session.setAttribute("flashMessage","leaguefail");
					response.sendRedirect("addLeague.jsp");
				}
			}
			else if (request.getParameter("form").equals("remove")){
				league = LeagueDAO.remove(league);
				if(league.isValid())
				{	
					session.setAttribute("flashMessage","leagueremoved");
					session.setAttribute("leagueName",name);
					response.sendRedirect("addLeague.jsp");
				}else{
					session.setAttribute("flashMessage","leagueremovefail");
					response.sendRedirect("addLeague.jsp");
				}
			}
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}
	

}
