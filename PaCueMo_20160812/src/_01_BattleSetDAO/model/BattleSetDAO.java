package _01_BattleSetDAO.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import _00_initial_Servise.GlobalService;

public class BattleSetDAO implements BattleSetDAO_interface
{
	String driver = GlobalService.DRIVER_NAME;
	String url = GlobalService.DB_URL;
	String userid = GlobalService.USERID;
	String passwd = GlobalService.PASSWORD;
	//========================================
	private static final String QUERY_ALL_STMT = "SELECT battleId , battleDateTime , homeId , awayId , homeScore , awayScore , homebet , awaybet FROM BattleSet;";
	private static final String QUERY_ONE_STMT = "SELECT battleId , battleDateTime , homeId , awayId , homeScore , awayScore , homebet , awaybet "
			+ "                                     FROM BattleSet WHERE battleId = ?";
	private static final String INSERT_STMT = "INSERT INTO BattleSet ( battleDateTime, homeId, awayId, homeScore, awayScore, homebet, awaybet )" +
			"   VALUES ( ? , ? , ? , ? , ? , ? ,? )";
	private static final String DELETE_STMT = "DELETE FROM BattleSet WHERE battleId = ?";
	private static final String UPDATE_STMT = "UPDATE BattleSet SET battleDateTime= ? ,homeId= ?,awayId= ?,homeScore= ?,awayScore= ?,homebet= ?,awaybet= ?" +
			"                                    WHERE battleId= ?";
	private static final String QUERY_BY_TEAMNAME_STMT = "SELECT battleId , battleDateTime , homeId , awayId , homeScore , awayScore , homebet , awaybet FROM BattleSet"
			+ "                                             JOIN NBATeam "
			+ "                                             ON NBATeam.teamId = BattleSet.homeId"
			+ "                                             WHERE homeId=? OR awayId=?"
			+ "                                             ORDER BY battleDateTime DESC";
	private static final String QUERY_BY_DATE_STMT = "SELECT battleId , battleDateTime , homeId , awayId , homeScore , awayScore , homebet , awaybet FROM BattleSet WHERE battleDateTime BETWEEN ? AND ?";

	//========================================
	@Override
	public void insert(BattleSetVO vo)
	{
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(INSERT_STMT);)
		{
			pStmt.setTimestamp(1, vo.getBattleDateTime());
			pStmt.setInt(2, vo.getHomeId());
			pStmt.setInt(3, vo.getAwayId());
			pStmt.setInt(4, vo.getHomeScore());
			pStmt.setInt(5, vo.getAwayScore());
			pStmt.setDouble(6, vo.getHomebet());
			pStmt.setDouble(7, vo.getAwaybet());
			int num = pStmt.executeUpdate();
			System.out.println("=== 新增BattleSet " + num + " 筆 ===");
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void delete(Integer battleId)
	{
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(DELETE_STMT);)
		{
			pStmt.setInt(1, battleId);
			int num = pStmt.executeUpdate();
			System.out.println("=== 刪除BattleSet " + num + " 筆 ===");
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void update(BattleSetVO vo)
	{
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(UPDATE_STMT);)
		{
			pStmt.setTimestamp(1, vo.getBattleDateTime());
			pStmt.setInt(2, vo.getHomeId());
			pStmt.setInt(3, vo.getAwayId());
			pStmt.setInt(4, vo.getHomeScore());
			pStmt.setInt(5, vo.getAwayScore());
			pStmt.setDouble(6, vo.getHomebet());
			pStmt.setDouble(7, vo.getAwaybet());
			pStmt.setInt(8, vo.getBattleId());
			int num = pStmt.executeUpdate();
			System.out.println(" ======== BattleSet 修改 " + num + " 筆成功 ======== ");
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public BattleSetVO findByPrimaryKey(Integer battleId)
	{
		ResultSet rs = null;
		BattleSetVO vo = null;
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(QUERY_ONE_STMT);)
		{
			pStmt.setInt(1, battleId);
			rs = pStmt.executeQuery();
			if (rs.next())
			{
				vo = new BattleSetVO();
				vo.setBattleId(rs.getInt("battleId"));
				vo.setBattleDateTime(rs.getTimestamp("battleDateTime"));
				vo.setHomeId(rs.getInt("homeId"));
				vo.setAwayId(rs.getInt("awayId"));
				vo.setHomeScore(rs.getInt("homeScore"));
				vo.setAwayScore(rs.getInt("awayScore"));
				vo.setHomebet(rs.getDouble("homebet"));
				vo.setAwaybet(rs.getDouble("awaybet"));
			}
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

		return vo;
	}

	@Override
	public List<BattleSetVO> getAll()
	{
		ResultSet rs = null;
		List<BattleSetVO> list = new ArrayList<>();
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(QUERY_ALL_STMT);)
		{
			rs = pStmt.executeQuery();
			while (rs.next())
			{
				BattleSetVO vo = new BattleSetVO();
				vo.setBattleId(rs.getInt("battleId"));
				vo.setBattleDateTime(rs.getTimestamp("battleDateTime"));
				vo.setHomeId(rs.getInt("homeId"));
				vo.setAwayId(rs.getInt("awayId"));
				vo.setHomeScore(rs.getInt("homeScore"));
				vo.setAwayScore(rs.getInt("awayScore"));
				vo.setHomebet(rs.getDouble("homebet"));
				vo.setAwaybet(rs.getDouble("awaybet"));
				list.add(vo);
			}
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<BattleSetVO> getSetsById(Integer battleId)
	{
		ResultSet rs = null;
		List<BattleSetVO> list = new ArrayList<>();
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(QUERY_BY_TEAMNAME_STMT);)
		{
			pStmt.setInt(1, battleId);
			pStmt.setInt(2, battleId);
			rs = pStmt.executeQuery();

			while (rs.next())
			{
				BattleSetVO vo = new BattleSetVO();
				vo.setBattleId(rs.getInt("battleId"));
				vo.setBattleDateTime(rs.getTimestamp("battleDateTime"));
				vo.setHomeId(rs.getInt("homeId"));
				vo.setAwayId(rs.getInt("awayId"));
				vo.setHomeScore(rs.getInt("homeScore"));
				vo.setAwayScore(rs.getInt("awayScore"));
				vo.setHomebet(rs.getDouble("homebet"));
				vo.setAwaybet(rs.getDouble("awaybet"));
				list.add(vo);
			}
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<BattleSetVO> getSetsByDate(String queryDate)
	{
		ResultSet rs = null;
		List<BattleSetVO> list = new ArrayList<>();
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(QUERY_BY_DATE_STMT);)
		{
			Calendar calendar = Calendar.getInstance();
			String[] qDate = queryDate.split("-");
			Integer yyyy = Integer.valueOf(qDate[0]);
			Integer mm = Integer.valueOf(qDate[1]);
			Integer dd = Integer.valueOf(qDate[2]);

			/**
			 ** Calendar.YEAR 代表加減年
			 ** Calendar.MONTH 代表加減月份
			 ** Calendar.DATE 代表加減天數
			 ** Calendar.HOUR 代表加減小時數
			 ** Calendar.MINUTE 代表加減分鐘數
			 ** Calendar.SECOND 代表加減秒數
			 **/
			calendar.set(yyyy, mm - 1, dd);//設定時間為輸入的QueryDate (※ 注意: Calendar 月份 -1)
			calendar.add(Calendar.DATE, 1);
			//----------------------------------
			String date1 = queryDate;
			String date2 = (new Date(calendar.getTimeInMillis())).toString();
//			System.out.println("date1 : ---" + date1);
//			System.out.println("date2 : ---" + date2);
			//----------------------------------
			pStmt.setString(1, date1);
			pStmt.setString(2, date2);
			rs = pStmt.executeQuery();

			while (rs.next())
			{
				BattleSetVO vo = new BattleSetVO();
				vo.setBattleId(rs.getInt("battleId"));
				vo.setBattleDateTime(rs.getTimestamp("battleDateTime"));
				vo.setHomeId(rs.getInt("homeId"));
				vo.setAwayId(rs.getInt("awayId"));
				vo.setHomeScore(rs.getInt("homeScore"));
				vo.setAwayScore(rs.getInt("awayScore"));
				vo.setHomebet(rs.getDouble("homebet"));
				vo.setAwaybet(rs.getDouble("awaybet"));
				list.add(vo);
			}
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args)
	{

//		Calendar calendar = Calendar.getInstance();
//
//		calendar.set(2016, 7, 6);//設定時間為輸入的QueryDate
//		calendar.add(Calendar.DATE, 1);
//		String date2 = (new Date(calendar.getTimeInMillis())).toString();
//
//		System.out.println(date2);
//		----------依日期查詢----------------
		BattleSetDAO dao = new BattleSetDAO();
		List<BattleSetVO> mylist = dao.getSetsByDate("2015-11-06");
		for (BattleSetVO vo : mylist)
		{
			System.out.println(vo.getBattleId() + "  " + vo.getHomeId() + " vs " + vo.getAwayId());
		}

		//----------依隊名查詢----------------
//		BattleSetDAO dao = new BattleSetDAO();
//		List<BattleSetVO> mylist = dao.getSetsById(7);
//		for (BattleSetVO vo : mylist)
//		{
//			System.out.println(vo.getBattleId() + "  " + vo.getHomeId() + " vs " + vo.getAwayId());
//		}
		//----------end依對名查詢----------------

//		BattleSetDAO dao = new BattleSetDAO();
//		List<BattleSetVO> list = dao.getAll();
//		for (BattleSetVO vo : list)
//		{
//			System.out.println(vo.getBattleDateTime() + "    " + vo.getHomeScore() + " __  " + vo.getAwayScore());
//		}

//		BattleSetDAO dao = new BattleSetDAO();
//		System.out.println(dao.findByPrimaryKey(3).getBattleDateTime());

//		BattleSetDAO dao = new BattleSetDAO();
//		BattleSetVO vo = new BattleSetVO();
//		vo.setBattleDateTime(java.sql.Timestamp.valueOf("2016-04-27 08:05:33"));
//		vo.setHomeId(5);
//		vo.setAwayId(7);
//		vo.setHomeScore(77);
//		vo.setAwayScore(80);
//		vo.setHomebet(9000.0);
//		vo.setAwaybet(9500.0);
//
//		dao.insert(vo);
//

//		BattleSetDAO dao = new BattleSetDAO();
//		dao.delete(13);
//		List<BattleSetVO> list = dao.getAll();
//		for (BattleSetVO vv : list)
//		{
//			System.out.println(vv.getBattleId() + "   " + vv.getBattleDateTime());
//		}

//		BattleSetDAO dao = new BattleSetDAO();
//
//		BattleSetVO vo = new BattleSetVO();
//		vo.setBattleId(1);
//		vo.setBattleDateTime(java.sql.Timestamp.valueOf("2000-12-01 01:05:08"));
//		vo.setHomeId(19);
//		vo.setAwayId(1);
//		vo.setHomeScore(98);
//		vo.setAwayScore(60);
//		vo.setHomebet(19000.0);
//		vo.setAwaybet(93500.0);
//
//		dao.update(vo);
//		List<BattleSetVO> list = dao.getAll();
//		for (BattleSetVO vv : list)
//		{
//			System.out.println(vv.getBattleId() + "   " + vv.getBattleDateTime());
//		}

	}

}
