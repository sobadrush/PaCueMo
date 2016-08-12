package _01_NBATeam.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _00_initial_Servise.GlobalService;

public class NBATeamDAO implements NBATeamDAO_interface
{
	String driver = GlobalService.DRIVER_NAME;
	String url = GlobalService.DB_URL;
	String userid = GlobalService.USERID;
	String passwd = GlobalService.PASSWORD;

	private static final String GET_BY_ID_STMT = "SELECT teamId , teamName , teamLogoURL FROM NBATeam" +
			"                                       WHERE teamId=?";
	private static final String GET_BY_NAME_STMT = "SELECT teamId , teamName , teamLogoURL FROM NBATeam" +
			"                                         WHERE teamName like ? ";
	private static final String GET_ALL_STMT = "SELECT teamId , teamName , teamLogoURL FROM NBATeam;";

	public static void main(String[] args)
	{
//		NBATeamDAO dao = new NBATeamDAO();
//		dao.findByTeamName("123");

//		NBATeamDAO dao = new NBATeamDAO();
//		List<NBATeamVO> list = dao.getAll();
//
//		for (NBATeamVO vo : list)
//		{
//			System.out.println(vo.getTeamID() + "  " + vo.getTeamName());
//		}

//		NBATeamDAO dao = new NBATeamDAO();
//
//		System.out.println(dao.findByTeamId(1).getTeamName());
//		System.out.println(dao.findByTeamName("夏洛特黃蜂").getTeamLogoURL());
	}

	@Override
	public NBATeamVO findByTeamId(Integer teamID)
	{
		ResultSet rs = null;
		NBATeamVO nbaVO = null;
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(GET_BY_ID_STMT);)
		{
			pStmt.setInt(1, teamID);
			rs = pStmt.executeQuery();
			if (rs.next())
			{
				nbaVO = new NBATeamVO();
				nbaVO.setTeamID(rs.getInt("teamId"));
				nbaVO.setTeamName(rs.getString("teamName"));
				nbaVO.setTeamLogoURL(rs.getString("teamLogoURL"));

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

		return nbaVO;
	}

	@Override
	public NBATeamVO findByTeamName(String teamName)
	{
		ResultSet rs = null;
		NBATeamVO nbaVO = null;
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(GET_BY_NAME_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);)
		{
			pStmt.setString(1, "%" + teamName + "%");
			rs = pStmt.executeQuery();

			rs.last();
			if (rs.getRow() == 0)// 查無資料
			{
				return null;
			}

			rs.beforeFirst();
			if (rs.next())
			{
				nbaVO = new NBATeamVO();
				nbaVO.setTeamID(rs.getInt("teamId"));
				nbaVO.setTeamName(rs.getString("teamName"));
				nbaVO.setTeamLogoURL(rs.getString("teamLogoURL"));
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

		return nbaVO;
	}

	@Override
	public List<NBATeamVO> getAll()
	{
		ResultSet rs = null;
		List<NBATeamVO> list = new ArrayList<>();
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			throw new RuntimeException("Couldn't load database driver. " + ex.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pStmt = conn.prepareStatement(GET_ALL_STMT);)
		{
			rs = pStmt.executeQuery();
			while (rs.next())
			{
				NBATeamVO nbaVO = new NBATeamVO();
				nbaVO.setTeamID(rs.getInt("teamId"));
				nbaVO.setTeamName(rs.getString("teamName"));
				nbaVO.setTeamLogoURL(rs.getString("teamLogoURL"));
				list.add(nbaVO);
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

}
