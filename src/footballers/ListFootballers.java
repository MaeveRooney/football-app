package footballers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public Map<String, String> filterPlayers(int teamID, String position){
    	Statement stmt = null;
		String searchQuery = null;
		if (teamID == 0){
			searchQuery = "Select * from footballers where id not in (Select distinct playerID from team_players)";
		} else {
			if (position.matches("goalie")) {
				searchQuery = "select id, full_name, goals, strength, speed from footballers " +
							"inner join team_players " +
							"on footballers.id=team_players.playerID " +
							"where team_players.teamID="+teamID;
			} else if (position.matches("defense")) {
				searchQuery = "select id, full_name, defense, strength, speed from footballers " +
						"inner join team_players " +
						"on footballers.id=team_players.playerID " +
						"where team_players.teamID="+teamID;
			} else if (position.matches("mid")) {
				searchQuery = "select id, full_name, passing, strength, speed from footballers " +
						"inner join team_players " +
						"on footballers.id=team_players.playerID " +
						"where team_players.teamID="+teamID;
			} else if (position.matches("attack")) {
				searchQuery = "select id, full_name, scoring, strength, speed from footballers " +
						"inner join team_players " +
						"on footballers.id=team_players.playerID " +
						"where team_players.teamID="+teamID;
			}
			else {
				searchQuery = "select id, full_name from footballers " +
						"inner join team_players " +
						"on footballers.id=team_players.playerID " +
						"where team_players.teamID="+teamID;
			}
		}
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				map.put("", "");
				while(rs.next()){
					if (position.matches("defense")){
						int sum = rs.getInt("defense") + rs.getInt("speed") + rs.getInt("strength");
						String value = rs.getString("full_name") + " | " + sum + "/30";
						map.put(rs.getString("full_name"), value);
					} else if (position.matches("goalie")) { 
						int sum = rs.getInt("goals") + rs.getInt("speed") + rs.getInt("strength");
						String value = rs.getString("full_name") + " | " + sum + "/30";
						map.put(rs.getString("full_name"), value);
					} else if (position.matches("mid")) {
						int sum = rs.getInt("passing") + rs.getInt("speed") + rs.getInt("strength");
						String value = rs.getString("full_name") + " | " + sum + "/30";
						map.put(rs.getString("full_name"), value);
					} else if (position.matches("attack")) {
						int sum = rs.getInt("scoring") + rs.getInt("speed") + rs.getInt("strength");
						String value = rs.getString("full_name") + " | " + sum + "/30";
						map.put(rs.getString("full_name"), value);
					} else {
						map.put(rs.getString("full_name"), rs.getString("full_name"));
					}
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		
		return map;
    }
    
    public List<HashMap<String,String>> getPlayerInfoForTeam(int teamID){
    	List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
    	Statement stmt = null;
		String searchQuery = "select * from footballers " +
					"inner join team_players " +
					"on footballers.id=team_players.playerID " +
					"where team_players.teamID="+teamID;			
		try
		{
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.isBeforeFirst() ) { 
				while(rs.next()){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("fullName", rs.getString("full_name"));
					map.put("shirtNumber", rs.getString("shirtNumber"));
					map.put("speed", rs.getString("speed"));
					map.put("strength", rs.getString("strength"));
					map.put("passing", rs.getString("passing"));
					map.put("defense", rs.getString("defense"));
					map.put("scoring", rs.getString("scoring"));
					map.put("goals", rs.getString("goals"));
					list.add(map);
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 
		
		return list;
    }
    
}
