package footballers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import login.ConnectionManager;

public class ListFootballers {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static Map<Integer, String> map = null;
	
    public Map<Integer, String> getItems() {
    	Statement stmt = null;
		String searchQuery = "Select * from footballers";
	
		Map<Integer, String> map = new HashMap<Integer, String>();

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				map.put(new Integer(0),"");
				while(rs.next()){
					map.put(new Integer(rs.getInt("id")), rs.getString("full_name"));
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		
		return map;
    }
    
    public Map<Integer, String> filterPlayers(int teamID){
    	Statement stmt = null;
		String searchQuery;
		if (teamID == 0){
			searchQuery = "Select * from footballers where id not in (Select distinct playerID from team_players)";
		}
		else {
			searchQuery = "select * from footballers " +
					"inner join team_players " +
					"on footballers.id=team_players.playerID " +
					"where team_players.teamID="+teamID;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				map.put(new Integer(0),"");
				while(rs.next()){
					map.put(new Integer(rs.getInt("id")), rs.getString("full_name"));
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		
		return map;
    }
    
}
