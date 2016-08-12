package _01_BattleSetDAO.model;

public class BattleSetVO
{
	private Integer battleId;
	private java.sql.Timestamp battleDateTime;
	private Integer homeId;
	private Integer awayId;
	private Integer homeScore;
	private Integer awayScore;
	private Double homebet;
	private Double awaybet;

	public Integer getBattleId()
	{
		return battleId;
	}

	public void setBattleId(Integer battleId)
	{
		this.battleId = battleId;
	}

	public java.sql.Timestamp getBattleDateTime()
	{
		return battleDateTime;
	}

	public void setBattleDateTime(java.sql.Timestamp battleDateTime)
	{
		this.battleDateTime = battleDateTime;
	}

	public Integer getHomeId()
	{
		return homeId;
	}

	public void setHomeId(Integer homeId)
	{
		this.homeId = homeId;
	}

	public Integer getAwayId()
	{
		return awayId;
	}

	public void setAwayId(Integer awayId)
	{
		this.awayId = awayId;
	}

	public Integer getHomeScore()
	{
		return homeScore;
	}

	public void setHomeScore(Integer homeScore)
	{
		this.homeScore = homeScore;
	}

	public Integer getAwayScore()
	{
		return awayScore;
	}

	public void setAwayScore(Integer awayScore)
	{
		this.awayScore = awayScore;
	}

	public Double getHomebet()
	{
		return homebet;
	}

	public void setHomebet(Double homebet)
	{
		this.homebet = homebet;
	}

	public Double getAwaybet()
	{
		return awaybet;
	}

	public void setAwaybet(Double awaybet)
	{
		this.awaybet = awaybet;
	}

}
