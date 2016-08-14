package _02_GoodsOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _02_GoodsOrder.model.GoodsOrderService;
import _02_GoodsOrder.model.GoodsOrderVO;

@WebServlet("/_01_Gambling/GoodsOrder_Servlet.do")
public class GoodsOrder_Servlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null; // for ajax
		String action = request.getParameter("action");
		//---------------------------------------------------------------------------
		if ("buyCoins".equals(action))
		{

			try
			{
				//=============== 【session 中get 會員id】 ====================
//				HttpSession session = request.getSession();
//				Integer memberId = (Integer) session.getAttribute("memberId");
				Integer memberId = 17;
				//==== 付款狀態 ===
				Boolean ispay = true;
				//=============================================
				String cardNum = request.getParameter("cardNum");
				String fullName = request.getParameter("fullName");
				String expire = request.getParameter("expire");
				String cvc = request.getParameter("cvc");
				String NTD = request.getParameter("NTD");
				String coin = request.getParameter("coin");
				String bookingTime = request.getParameter("bookingTime");
				//=============== 【型態轉換】 ====================

				Integer int_cvc = null;
				try
				{
					int_cvc = Integer.valueOf(cvc);
				}
				catch (NumberFormatException e)
				{
					int_cvc = 0;
					System.out.println(" === GoodsOrder_Servlet.java ==== L52 cvc轉換數字失敗");
				}

				Integer int_NTD = null;
				try
				{
					int_NTD = new Integer(NTD);
				}
				catch (NumberFormatException e)
				{
					int_NTD = 0;
					System.out.println(" === GoodsOrder_Servlet.java ==== L64 NTD 轉換數字失敗");
				}

				Integer int_coin = null;
				try
				{
					int_coin = new Integer(coin);
				}
				catch (NumberFormatException e)
				{
					int_coin = 0;
					System.out.println(" === GoodsOrder_Servlet.java ==== L74 coin 轉換數字失敗");
				}

				java.sql.Timestamp bookingSQLTime = null;
				try
				{
					bookingSQLTime = java.sql.Timestamp.valueOf(bookingTime);
				}
				catch (IllegalArgumentException e)
				{
					bookingSQLTime = new java.sql.Timestamp(System.currentTimeMillis());
					System.out.println(" === GoodsOrder_Servlet.java ==== L87 bookingTime 轉換數字失敗");
				}

				// =================== 格式錯誤驗證交給javascript dialogy做 ===========================
				GoodsOrderService svc = new GoodsOrderService();
				GoodsOrderVO vo = new GoodsOrderVO();

				vo.setMemberId(memberId);
				vo.setCardNum(cardNum);
				vo.setFullName(fullName);
				vo.setExpire(expire);
				vo.setCvc(int_cvc);
				vo.setNtdQty(int_NTD);
				vo.setCoinQty(int_coin);
				vo.setOrderDateTime(bookingSQLTime);
				vo.setIsPay(ispay);
				svc.addGoodsOrder(vo);

				out = response.getWriter();
				out.print("GGG");
			}
			catch (Exception e)// 其他可能錯誤處理
			{
				/*
				 * //【導向錯誤頁面】
				 * RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
				 * failureView.forward(req, res);
				 * return;
				 */
				System.out.println("=== GoodsOrder_Servlet.java 發生異常錯誤 L109===");
			}

		}
	}

}
