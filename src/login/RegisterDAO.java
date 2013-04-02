package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static LoginBean register(LoginBean bean)
	{
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String fullname = bean.getFullName();
		
		//this string does extract the greatest id	
		String queryLastManagerID="SELECT * from managers order by id desc LIMIT 1";
		//string to insert data into the database;

		String insertQuery = "insert into managers (fullName, username, password) values ('" + fullname + "','" + username + "','" + password + "')";

		try
		{
			int lastID = 1;
			int newID = 0;
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			
			// get highest manager id
			ps = currentCon.prepareStatement(queryLastManagerID);
			rs = ps.executeQuery();
			if (rs.next()){
				lastID = rs.getInt("id");
				System.out.println("Old id " + lastID);
			}
			
			// insert manager to db
			ps = currentCon.prepareStatement(insertQuery);
			ps.executeUpdate();

			// get new manager id
			ps = currentCon.prepareStatement(queryLastManagerID);
			rs = ps.executeQuery();
			if (rs.next()){
				newID = rs.getInt("id");
				System.out.println("New id " + newID);
			}
			
			if (newID <= lastID)
			{
				System.out.println("Manager not inserted");
				bean.setValid(false);
			}
			else
			{				
				String fullName = rs.getString("fullName");
				System.out.println("Welcome " + fullName);
				bean.setFullName(fullName);
				bean.setValid(true);
			}

		}
		catch (Exception ex)
		{
			System.out.println("Registration failed: An Exception has occurred! " + ex);
		}
		return bean;
	}
}