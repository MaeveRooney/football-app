package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	static Connection conn;

	public static Connection getConnection()
	{
		try
		{
			String url = "jdbc:mysql://localhost:8888/";
			String dbName ="football";
			String uname = "myuser";
			String pwd = "password";

			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				conn = DriverManager.getConnection(url+dbName,uname,pwd);
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
		return conn;
	}

}