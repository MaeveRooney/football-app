package teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import login.ConnectionManager;

public class TeamDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static TeamBean register(TeamBean bean)
	{		
		String name = bean.getName();
		String league = bean.getLeague();
		System.out.println("Team name  " + name);
		System.out.println("League name  " + league);
		
		String queryTeams="SELECT * from teams order by id desc LIMIT 1";
		//this string does extract the id of team	
		String queryLeague="SELECT * from leagues WHERE name = '" + league + "' LIMIT 1";
		System.out.println(queryLeague);
		try
		{
			int leagueID = 0;
			int lastID = 0;
			int newID = 0;
						
			//connecting to the DB
			currentCon = ConnectionManager.getConnection();
			
			// get highest player id
			ps = currentCon.prepareStatement(queryLeague);
			rs = ps.executeQuery();
			if (rs.next()){
				leagueID = rs.getInt("id");
				System.out.println("League id " + leagueID);
			}
			
			//string to insert data into the database;
			String insertQuery = "insert into teams (name, leagueID) values ('" + name+ "','" + leagueID + "')";

			if (leagueID != 0){
				// insert player and team to db
				// get highest league id
				ps = currentCon.prepareStatement(queryTeams);
				rs = ps.executeQuery();
				if (rs.next()){
					lastID = rs.getInt("id");
					System.out.println("Old id " + lastID);
				}
				
				// insert league to db
				ps = currentCon.prepareStatement(insertQuery);
				ps.executeUpdate();

				// get new league id
				ps = currentCon.prepareStatement(queryTeams);
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
					System.out.println("team added to db");
				}			
			}
			

		}
		catch (Exception ex)
		{
			bean.setValid(false);
			System.out.println("Adding team to db failed: An Exception has occurred! " + ex);
		}

		return bean;
	}
}
