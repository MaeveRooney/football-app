package teams;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import login.ConnectionManager;

public class ListTeams {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public Map<Integer, String> getItems() {

		Statement stmt = null;
		String searchQuery = "select * from teams";
		Map<Integer, String> map = new HashMap<Integer, String>();

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				while(rs.next()){
					map.put(new Integer(rs.getInt("id")), rs.getString("name"));
				}
				return map;
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		map.put(new Integer(0),"no teams in database. please add some teams");
		return map;
    }

}
