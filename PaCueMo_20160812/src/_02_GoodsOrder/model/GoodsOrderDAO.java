package _02_GoodsOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _00_initial_Servise.GlobalService;

public class GoodsOrderDAO implements GoodsOrderDAO_interface
{
	String driver = GlobalService.DRIVER_NAME;
	String url = GlobalService.DB_URL;
	String userid = GlobalService.USERID;
	String passwd = GlobalService.PASSWORD;

	//========================================
	private static final String INSERT_STMT = "INSERT INTO GoodsOrder ( memberId , cardNum , fullName , expire ,cvc , ntdQty , coinQty , orderDateTime , isPay)"
			+ "                                  VALUES   ( ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	private static final String QUERY_ALL_STMT = "SELECT orderId , memberId , cardNum , fullName , expire ,cvc , ntdQty , coinQty , orderDateTime , isPay FROM GoodsOrder";

	//========================================
	public static void main(String[] args)
	{
		//===========insert test =============
//		GoodOrderVO myvo = new GoodOrderVO();
//		myvo.setMemberId(120);
//		myvo.setCardNum("4023 0555 7897 3546");
//		myvo.setFullName("火雲邪神");
//		myvo.setExpire("07/2018");
//		myvo.setCvc(353);
//		myvo.setNtdQty(500);
//		myvo.setCoinQty(5000);
//		myvo.setOrderDateTime(java.sql.Timestamp.valueOf("2016-08-14 17:18:04"));
//		myvo.setIsPay(true);
//		
//		GoodsOrderDAO dao = new GoodsOrderDAO();
//		dao.insert(myvo);
		//============ end of insert test ========
		//===========getAll test =============
//		GoodsOrderDAO dao = new GoodsOrderDAO();
//		List<GoodOrderVO> list = dao.getAll();
//		for (GoodOrderVO vvo : list)
//		{
//			System.out.println(vvo.getFullName() + "   " + vvo.getCardNum());
//		}
		//=========== end of getAll test =============
	}

	@Override
	public void insert(GoodsOrderVO vo)
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
			pStmt.setInt(1, vo.getMemberId());
			pStmt.setString(2, vo.getCardNum());
			pStmt.setString(3, vo.getFullName());
			pStmt.setString(4, vo.getExpire());
			pStmt.setInt(5, vo.getCvc());
			pStmt.setInt(6, vo.getNtdQty());
			pStmt.setInt(7, vo.getCoinQty());
			pStmt.setTimestamp(8, vo.getOrderDateTime());
			pStmt.setBoolean(9, vo.getIsPay());

			int num = pStmt.executeUpdate();
			System.out.println("=== 新增 GoodOrder " + num + " 筆 ===");
		}
		catch (SQLException se)
		{
			//se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void update(GoodsOrderVO vo)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer orderId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public GoodsOrderVO findByPrimaryKey(Integer orderId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsOrderVO> getAll()
	{
		List<GoodsOrderVO> list = new ArrayList<>();
		ResultSet rs = null;
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
				GoodsOrderVO vo = new GoodsOrderVO();
				vo.setOrderId(rs.getInt("orderId"));
				vo.setMemberId(rs.getInt("memberId"));
				vo.setCardNum(rs.getString("cardNum"));
				vo.setFullName(rs.getString("fullName"));
				vo.setExpire(rs.getString("expire"));
				vo.setCvc(rs.getInt("cvc"));
				vo.setNtdQty(rs.getInt("ntdQty"));
				vo.setCoinQty(rs.getInt("coinQty"));
				vo.setOrderDateTime(rs.getTimestamp("orderDateTime"));
				vo.setIsPay(rs.getBoolean("isPay"));
				list.add(vo);
			}

			System.out.println("=== 查詢 GoodOrder : getAll() 成功 ===");
		}
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

}
