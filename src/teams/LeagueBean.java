package teams;

//Data Encapsulation using Getters and Setters
public class LeagueBean {
	private String name;
	public boolean valid;

	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
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