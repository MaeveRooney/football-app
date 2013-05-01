package formation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChooseTeamAndFormation
 */
@WebServlet("/ChooseFormation")
public class ChooseFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get current session, or initialize one if none
		HttpSession session = request.getSession(true);		
		try
		{
			System.out.println("In the choose formation Servlet");
			String formation = request.getParameter("formation");
			String delims = "[,]";
			String[] positions = formation.split(delims);
			session.setAttribute("defense", Integer.parseInt(positions[0]));
			session.setAttribute("mid", Integer.parseInt(positions[1]));
			session.setAttribute("attack", Integer.parseInt(positions[2]));
			response.sendRedirect("SelectPlayers");

		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}

}
