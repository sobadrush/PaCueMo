package _01_NBATeam.model;

public class NBATeamVO
{
	private Integer teamID;
	private String teamName;
	private String teamLogoURL;

	public Integer getTeamID()
	{
		return teamID;
	}

	public void setTeamID(Integer teamID)
	{
		this.teamID = teamID;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	public String getTeamLogoURL()
	{
		return teamLogoURL;
	}

	public void setTeamLogoURL(String teamLogoURL)
	{
		this.teamLogoURL = teamLogoURL;
	}

}
