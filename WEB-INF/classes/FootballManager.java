
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FootballManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;


		try
		{
			// This will load the MySQL driver, each DB has its own driver
      		Class.forName("com.mysql.jdbc.Driver");

			// Get a connection to the database
			con = DriverManager.getConnection("jdbc:mysql://localhost:8888/ebookshop", "myuser", "password");

			// Create a Statement object
			stmt = con.createStatement();

			// Execute a SQL query, get a ResultSet
			rs = stmt.executeQuery("SELECT * FROM books");

			// Display the result set as a list
			out.println("<HTML><HEAD><TITLE>Phonebook</TITLE><HEAD>");
			out.println("<BODY>");
			out.println("<UL>");

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
         	//  For each row, retrieve the contents of the cells with getXxx(columnName).
         	System.out.println("The records selected are:");
         	int rowCount = 0;
         	while(rs.next()) {   // Move the cursor to the next row
				String name = rs.getString("name");
				double rating = rs.getDouble("rating");
				out.println("<li>" + name + ", " + rating + "</li>");
				rowCount++;
			 }
			 System.out.println("Total number of records = " + rowCount);

			out.println("<UL>");
			out.println("</BODY></HTML>");
		}
		catch(ClassNotFoundException e)
		{
			out.println("Could not load Database Driver : " + e.getMessage());
		}
		catch(SQLException e)
		{
			out.println("SQL Exception caught : " + e.getMessage());
		}
		finally
		{
			// Always close the database connection
			try
			{
				// if database connection is still open
				if (con != null)
				{
					con.close();
				}
			}
			catch(SQLException ignored)
			{
			}

		} // end finally
	} // end doGet()


}
