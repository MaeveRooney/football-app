package footballers;

//Data Encapsulation using Getters and Setters
public class PlayerBean {
	private String fullName;
	private String shirtNumber;
	private int id;
	private int speed;
	private int strength;
	private int passing;
	private int scoring;
	private int defense;
	private int goals;
	public boolean valid;

	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String newFullName)
	{
		fullName = newFullName;
	}
	public String getShirtNumber()
	{
		return shirtNumber;
	}
	public void setShirtNumber(String newShirtNumber)
	{
		shirtNumber = newShirtNumber;
	}
	public int getID()
	{
		return id;
	}
	public void setID(int newID)
	{
		id = newID;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(String newSpeed)
	{
		speed = Integer.parseInt(newSpeed);
	}
	public int getStrength()
	{
		return strength;
	}
	public void setStrength(String newStrength)
	{
		strength = Integer.parseInt(newStrength);
	}
	public int getPassing()
	{
		return passing;
	}
	public void setPassing(String newPassing)
	{
		passing = Integer.parseInt(newPassing);
	}
	public int getScoring()
	{
		return scoring;
	}
	public void setScoring(String newScoring)
	{
		scoring = Integer.parseInt(newScoring);
	}
	public int getDefense()
	{
		return defense;
	}
	public void setDefense(String newDefense)
	{
		defense = Integer.parseInt(newDefense);
	}
	public int getGoals()
	{
		return goals;
	}
	public void setGoals(String newGoals)
	{
		goals = Integer.parseInt(newGoals);
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
