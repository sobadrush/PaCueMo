package _01_BattleSetDAO.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _01_NBATeam.model.NBATeamService;
import _01_NBATeam.model.NBATeamVO;

public class BattleSetService
{
	private BattleSetDAO dao = null;

	public BattleSetService()
	{
		dao = new BattleSetDAO();
	}

	public void addBattleSet(BattleSetVO vo)
	{
		dao.insert(vo);
	}

	public void updateBattleSet(BattleSetVO vo)
	{
		dao.update(vo);
	}

	public void deleteBattleSet(Integer battleId)
	{
		dao.delete(battleId);
	}

	public BattleSetVO getOneBattleSet(Integer battleId)
	{
		return dao.findByPrimaryKey(battleId);
	}

	public List<BattleSetVO> getAllBattleSet()
	{
		List<BattleSetVO> list = dao.getAll();
		return list;
	}

	public List<Map<String, NBATeamVO>> getLogoURLs(String queryDate)
	{
		List<BattleSetVO> list_BattleSet = dao.getAll();
		List<Map<String, NBATeamVO>> retern_list = new ArrayList<>();
		NBATeamService nbaSvc = new NBATeamService();
		for (BattleSetVO vo : list_BattleSet)
		{
			String battleDate = vo.getBattleDateTime().toString().substring(0, 10);

			if (queryDate.equals(battleDate))
			{
				int homeId = vo.getHomeId();
				NBATeamVO homeVO = nbaSvc.getByTeamId(homeId);

				int awayId = vo.getAwayId();
				NBATeamVO awayVO = nbaSvc.getByTeamId(awayId);

				Map<String, NBATeamVO> myMap = new HashMap<>();
				myMap.put("home", homeVO);
				myMap.put("away", awayVO);

				retern_list.add(myMap);
			}

		}
		return retern_list;
	}

	public List<Map<String, Object>> getSetsByName(String teamName)
	{
		List<Map<String, Object>> retern_list = new ArrayList<>();
		//------------【根據輸入的teamName找到對應的teamID】------------------------
		NBATeamService nbaSvc = new NBATeamService();

		if (nbaSvc.getByTeamName(teamName) == null)// 若輸入的teamName查不到，直接丟null
		{
			return null;
		}
		else
		{
			teamName = nbaSvc.getByTeamName(teamName).getTeamName();//根據輸入的隊名模糊查詢(Like)出正確隊名
		}

		List<NBATeamVO> list1 = nbaSvc.getAllTeam();
		int teamId = -1;
		for (NBATeamVO nbaTeamVO : list1)
		{
			String tmp_name = nbaTeamVO.getTeamName();
			if (tmp_name.equals(teamName))
			{
				teamId = nbaTeamVO.getTeamID();
//				System.out.println("id = " + teamId + "    name=" + nbaTeamVO.getTeamName());
				break;
			}
		}
		if (teamId == -1)//若在DB中找不到對應的ID , return null;
		{
			return null;
		}
		//------------【根據找到的teamID查詢有此ID的對戰場次】---------
		List<BattleSetVO> list_battleSet = dao.getSetsById(teamId);
		for (BattleSetVO vo : list_battleSet)
		{

			//----由此場次的 homeId、awayID 找到teamVO
			NBATeamVO homeVO = nbaSvc.getByTeamId(vo.getHomeId());
			NBATeamVO awayVO = nbaSvc.getByTeamId(vo.getAwayId());

			Map<String, Object> myMap = new HashMap<>();// 加入battleTime ， 將 Map<String, NBATeamVO> 改為 Map<String, Object>
			myMap.put("home", homeVO);
			myMap.put("away", awayVO);
			myMap.put("battleTime", vo.getBattleDateTime().toString().substring(0, 16));

			retern_list.add(myMap);
		}
		return retern_list;
	}

	public List<Map<String, NBATeamVO>> getSetsByDate(String queryDate)
	{
		List<Map<String, NBATeamVO>> return_list = new ArrayList<Map<String, NBATeamVO>>();
		List<BattleSetVO> list = dao.getSetsByDate(queryDate);
		NBATeamService nbaSvc = new NBATeamService();
		for (BattleSetVO vo : list)
		{
//			System.out.println(vo.getHomeId() + " vs " + vo.getAwayId());

			NBATeamVO home = nbaSvc.getByTeamId(vo.getHomeId());
			NBATeamVO away = nbaSvc.getByTeamId(vo.getAwayId());

			Map<String, NBATeamVO> myMap = new HashMap<>();
			myMap.put("home", home);
			myMap.put("away", away);
			return_list.add(myMap);
		}

		return return_list;
	}

	public static void main(String[] args)
	{
		//-------------依隊名查詢(加入 對戰時間)--------------
//		BattleSetService svc = new BattleSetService();
//		List<Map<String, Object>> list = svc.getSetsByName("小牛");
//		for (Map<String, Object> map : list)
//		{
//			String homeName = ((NBATeamVO) map.get("home")).getTeamName();
//			String awayName = ((NBATeamVO) map.get("away")).getTeamName();
//			String battletime = ((String) map.get("battleTime"));
//			System.out.println(homeName + "  vs  " + awayName + "  -----  time :  " + battletime);
//		}
		//-------------依日期查詢--------------
//		BattleSetService svc = new BattleSetService();
//		List<Map<String, NBATeamVO>> ans = svc.getSetsByDate("2015-11-06");
//		for (Map<String, NBATeamVO> map : ans)
//		{
//			String home = map.get("home").getTeamName();
//			String away = map.get("away").getTeamName();
//
//			System.out.println(home + " ---vs--- " + away);
//		}
		//-------------結束==依日期查詢==結束--------------
//		BattleSetService svc = new BattleSetService();
//		List<Map<String, NBATeamVO>> list = svc.getSetsByName("達拉斯小牛");
//		for (Map<String, NBATeamVO> map : list)
//		{
//			System.out.println(map.get("home").getTeamName() + "   vs   " + map.get("away").getTeamName());
//		}

//
//		List<Map<String, NBATeamVO>> list = svc.getLogoURLs();

//		for (Map<String, NBATeamVO> map : list)
//		{
//			System.out.println(map.get("home").getTeamLogoURL() + "      " + map.get("away").getTeamLogoURL());
//
//		}

//		BattleSetVO vo = new BattleSetVO();
//		vo.setBattleDateTime(java.sql.Timestamp.valueOf("2016-04-27 08:05:33"));
//		vo.setHomeId(5);
//		vo.setAwayId(7);
//		vo.setHomeScore(77);
//		vo.setAwayScore(80);
//		vo.setHomebet(9000.0);
//		vo.setAwaybet(9500.0);
//		svc.addBattleSet(vo);

//		vo.setBattleId(1);
//		svc.updateBattleSet(vo);

//		svc.deleteBattleSet(12);
//		BattleSetVO vo2 = svc.getOneBattleSet(4);
//		System.out.println(vo2.getHomeId() + "  " + vo2.getAwayId());

//		NBATeamService NBAsvc = new NBATeamService();
//		List<BattleSetVO> list = svc.getAllBattleSet();
//		for (BattleSetVO vvo : list)
//		{
//			System.out.println(vvo.getHomeId() + "   " + vvo.getAwayId());
//			NBATeamVO nbavo = NBAsvc.getByTeamId(vvo.getHomeId());
//			System.out.println(nbavo.getTeamLogoURL());
//		}
	}

}
