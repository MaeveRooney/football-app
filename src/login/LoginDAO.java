package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;

	public static LoginBean login(LoginBean bean)
	{
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from managers where username='" + username + "' AND password='" + password + "'";

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists)
			{
				System.out.println("Username/Password entered is Incorrect or User does not Exists.");
				bean.setValid(false);
			}
			else if (userExists)
			{
				String fullName = rs.getString("fullName");
				System.out.println("Welcome " + fullName);
				bean.setFullName(fullName);
				bean.setValid(true);
			}

		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		return bean;
	}
}