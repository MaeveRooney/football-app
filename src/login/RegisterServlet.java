package login;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		try
		{
			System.out.println("In the Register Servlet");
			LoginBean user = new LoginBean();
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			String fullName = request.getParameter("fullName");
			user.setUserName(uname);
			user.setPassword(password);
			user.setFullName(fullName);
			user = RegisterDAO.register(user);
			if(user.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser",user);
				response.sendRedirect("RegisterSuccess.jsp");
			}else
				response.sendRedirect("RegisterFailed.jsp");
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}

}