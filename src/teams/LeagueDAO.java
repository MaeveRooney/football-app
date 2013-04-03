package teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import login.ConnectionManager;

public class LeagueDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static LeagueBean register(LeagueBean bean)
	{		
		String name = bean.getName();
		System.out.println("League name  " + name);
		
		//this string does extract the highest id of leagues
		String queryLeagues="SELECT * from leagues order by id desc LIMIT 1";
		String insertQuery = "INSERT into leagues(name) values('"+name+"')";
		try
		{
			int lastID = 0;
			int newID = 0;
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			// get highest league id
			ps = currentCon.prepareStatement(queryLeagues);
			rs = ps.executeQuery();
			if (rs.next()){
				lastID = rs.getInt("id");
				System.out.println("Old id " + lastID);
			}
			
			// insert league to db
			ps = currentCon.prepareStatement(insertQuery);
			ps.executeUpdate();

			// get new league id
			ps = currentCon.prepareStatement(queryLeagues);
			rs = ps.executeQuery();
			if (rs.next()){
				newID = rs.getInt("id");
				System.out.println("New id " + newID);
			}
			
			if (newID <= lastID)
			{
				System.out.println("League not inserted");
				bean.setValid(false);
			}
			else
			{				
				bean.setValid(true);
			}
		}
		catch (Exception ex)
		{
			System.out.println("Registration of league failed: An Exception has occurred! " + ex);
		}
		return bean;
	}
	
	public static LeagueBean remove(LeagueBean bean)
	{		
		String name = bean.getName();
		System.out.println("League name  " + name);
		//get league id
		String getID = "SELECT * from leagues where name = '"+name+"' LIMIT 1";
		//this string does extract the highest id of leagues
		String countEntries = "SELECT COUNT(*) FROM leagues";
		String deleteQuery = "DELETE from leagues where name = '"+name+"'";
		try
		{
			int oldCount = 0;
			int newCount = 0;
			int leagueID = 0;
			
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			// get leagueID
			ps = currentCon.prepareStatement(getID);
			rs = ps.executeQuery();
			if (rs.next()){
				leagueID = rs.getInt("id");
				System.out.println("league id " + leagueID);
			}
			
			//query to remove teams from league to be deleted
			String removeTeams = "UPDATE teams set leagueID=null where leagueID ="+leagueID;
			
			// remove teams from league
			ps = currentCon.prepareStatement(removeTeams);
			ps.executeUpdate();
			
			// get current number entries
			ps = currentCon.prepareStatement(countEntries);
			rs = ps.executeQuery();
			if (rs.next()){
				oldCount = rs.getInt(1);
				System.out.println("Old count" + oldCount);
			}
			
			// insert league to db
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
				bean.setValid(true);
			}
			else
			{	
				System.out.println("League not removed");
				bean.setValid(false);							
			}
		}
		catch (Exception ex)
		{
			System.out.println("Removing of league failed: An Exception has occurred! " + ex);
		}
		return bean;
	}
}
