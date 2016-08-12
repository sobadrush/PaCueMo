package _01_NBATeam.model;

import java.util.List;

public class NBATeamService
{

	private NBATeamDAO dao;

	public NBATeamService()
	{
		dao = new NBATeamDAO();
	}

	public NBATeamVO getByTeamId(Integer teamID)
	{
		return dao.findByTeamId(teamID);
	}

	public NBATeamVO getByTeamName(String teamName)
	{
		return dao.findByTeamName(teamName);
	}

	public List<NBATeamVO> getAllTeam()
	{
		return dao.getAll();
	}

	public static void main(String[] args)
	{
		//-------------------------------------------------------beg 查無資料
//		NBATeamService svc = new NBATeamService();
//		svc.getByTeamName("123");
//		System.out.println(svc.getByTeamName("123"));
		//-------------------------------------------------------end 查無資料

//		NBATeamService svc = new NBATeamService();
//		NBATeamVO vo = svc.getByTeamName("黃蜂");
//		System.out.println(vo.getTeamName());

//		List<NBATeamVO> list = svc.getAllTeam();
//		for (NBATeamVO vo : list)
//		{
//			System.out.println(vo.getTeamID() + "  " + vo.getTeamName());
//		}
//		NBATeamService svc = new NBATeamService();
//		NBATeamVO vo = svc.getByTeamId(3);
//		System.out.println(vo.getTeamName());
	}

}
