package _01_NBATeam.model;

import java.util.List;

public interface NBATeamDAO_interface
{
	public NBATeamVO findByTeamId(Integer teamID);

	public NBATeamVO findByTeamName(String teamName);

	public List<NBATeamVO> getAll();
}
