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
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			System.out.println("In the Login Servlet");
			LoginBean user = new LoginBean();
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			user.setUserName(uname);
			user.setPassword(password);
			user = LoginDAO.login(user);
			HttpSession session = request.getSession(true);
			if(user.isValid())
			{				
				session.setAttribute("user",user);
				session.setAttribute("name",user.getFullName());
				response.sendRedirect("dashboard.jsp");
			}else
				session.setAttribute("flashMessage","loginfail");
				response.sendRedirect("LoginPage.jsp");
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}

}