package footballers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import login.ConnectionManager;

public class FootballerDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static PlayerBean register(PlayerBean bean)
	{		
		String fullname = bean.getFullName();
		String shirtNumber = bean.getShirtNumber();
		int speed = bean.getSpeed();
		int strength = bean.getStrength();
		int passing = bean.getPassing();
		int scoring = bean.getScoring();
		int defense = bean.getDefense();
		int goals = bean.getGoals();
		
		//this string does extract the greatest id	
		String queryLastPlayerID="SELECT * from footballers order by id desc LIMIT 1";
		//string to insert data into the database;

		String insertQuery = "insert into footballers (full_name, shirtNumber, speed, strength, scoring, passing, defense, goals) values ('" + fullname + "','" + shirtNumber + "','" + speed + "','" + strength + "','" + scoring + "','" + passing + "','" + defense + "','" + goals + "')";

		try
		{
			int lastID = 0;
			int newID = 0;
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			// get highest player id
			ps = currentCon.prepareStatement(queryLastPlayerID);
			rs = ps.executeQuery();
			if (rs.next()){
				lastID = rs.getInt("id");
				System.out.println("Old id " + lastID);
			}
			
			// insert player to db
			ps = currentCon.prepareStatement(insertQuery);
			ps.executeUpdate();

			// get new player id
			ps = currentCon.prepareStatement(queryLastPlayerID);
			rs = ps.executeQuery();
			if (rs.next()){
				newID = rs.getInt("id");
				System.out.println("New id " + newID);
			}
			
			if (newID <= lastID)
			{
				System.out.println("Player not inserted");
				bean.setValid(false);
			}
			else
			{				
				String fullName = rs.getString("full_name");
				System.out.println("Added " + fullName);
				bean.setID(newID);
				bean.setValid(true);
			}

		}
		catch (Exception ex)
		{
			System.out.println("Registration failed: An Exception has occurred! " + ex);
		}

		return bean;
	}
	
	//get team id and add playerid and teamid to team_player table
	public static void addToTeam(int playerID, String team){	
		System.out.println("trying to add player to team");

		try
		{
			int teamID = Integer.parseInt(team);
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			//string to insert data into the database;
			String insertQuery = "insert into team_players (teamID, playerID) values ('" + teamID + "','" + playerID + "')";

			if (teamID != 0){
				// insert player and team to db
				ps = currentCon.prepareStatement(insertQuery);
				ps.executeUpdate();
				System.out.println("player added to team");
			}
			

		}
		catch (Exception ex)
		{
			System.out.println("Adding player to team failed: An Exception has occurred! " + ex);
		}
	}
	
	public static Boolean removePlayer(String playerID)
	{		
		int id = Integer.parseInt(playerID);
		System.out.println("id  " + playerID);

		String countEntries = "SELECT COUNT(*) FROM footballers";
		String deleteQuery = "DELETE from footballers where id = "+id;
		try
		{
			int oldCount = 0;
			int newCount = 0;
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			//query to remove players from teams
			String removeTeamPlayers = "DELETE from team_players where playerID ="+id;
			
			// remove player from team
			ps = currentCon.prepareStatement(removeTeamPlayers);
			ps.executeUpdate();
			
			// get current number entries
			ps = currentCon.prepareStatement(countEntries);
			rs = ps.executeQuery();
			if (rs.next()){
				oldCount = rs.getInt(1);
				System.out.println("Old count" + oldCount);
			}
			
			// delete player from db
			ps = currentCon.prepareStatement(deleteQuery);
			ps.executeUpdate();

			// get new number entries
			ps = currentCon.prepareStatement(countEntries);
			rs = ps.executeQuery();
			if (rs.next()){
				newCount = rs.getInt(1);
				System.out.println("New count " + newCount);
			}
			
			if (newCount == oldCount-1)
			{
				return true;
			}
			else
			{	
				System.out.println("League not removed");
				return false;							
			}
		}
		catch (Exception ex)
		{
			System.out.println("Removing of league failed: An Exception has occurred! " + ex);
		}
		return false;
	}
}
