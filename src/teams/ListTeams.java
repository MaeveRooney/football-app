package teams;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import login.ConnectionManager;

public class ListTeams {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	
    public List<String> getItems() {

		Statement stmt = null;
		String searchQuery = "select * from teams";
		List<String> list = new ArrayList<String>();

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				list.add("");
				while(rs.next()){
					list.add(rs.getString("name"));
				}
				return list;
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		list.add("no teams in database. please add some teams");
		return list;
    }

}
