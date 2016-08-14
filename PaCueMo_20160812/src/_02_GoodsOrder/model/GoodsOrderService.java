package _02_GoodsOrder.model;

import java.util.List;

public class GoodsOrderService
{

	private GoodsOrderDAO dao = null;

	public GoodsOrderService()
	{
		dao = new GoodsOrderDAO();
	}

	public void addGoodsOrder(GoodsOrderVO vo)
	{
		dao.insert(vo);
	}

	public List<GoodsOrderVO> getAllGoodsOrder()
	{
		return dao.getAll();
	}

	public static void main(String[] args)
	{
		//===========insert test =============
//		GoodsOrderVO myvo = new GoodsOrderVO();
//		myvo.setMemberId(112);
//		myvo.setCardNum("6587 1111 8888 7777");
//		myvo.setFullName("哆啦A夢");
//		myvo.setExpire("01/2019");
//		myvo.setCvc(478);
//		myvo.setNtdQty(700);
//		myvo.setCoinQty(7000);
//		myvo.setOrderDateTime(java.sql.Timestamp.valueOf("2016-08-14 18:35:54"));
//		myvo.setIsPay(true);
//
//		GoodsOrderService svc = new GoodsOrderService();
//		svc.addGoodsOrder(myvo);
		//===========getAllGoodsOrder test =============
//		GoodsOrderService svc = new GoodsOrderService();
//
//		List<GoodsOrderVO> list = svc.getAllGoodsOrder();
//		for (GoodsOrderVO vvo : list)
//		{
//			System.out.println(vvo.getFullName() + "  " + vvo.getCardNum());
//		}
	}

}
