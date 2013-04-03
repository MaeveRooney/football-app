package teams;

//Data Encapsulation using Getters and Setters
public class TeamBean {
	private String name;
	private String league;
	public boolean valid;

	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public String getLeague()
	{
		return league;
	}
	public void setLeague(String newLeague)
	{
		league = newLeague;
	}
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean newValid)
	{
		valid = newValid;
	}
}