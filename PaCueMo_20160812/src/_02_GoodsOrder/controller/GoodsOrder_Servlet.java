package _02_GoodsOrder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String action = request.getParameter("action");
		//---------------------------------------------------------------------------
		if ("buyCoins".equals(action))
		{
			String cardNum = request.getParameter("cardNum");
			System.out.println(cardNum);
			String fullName = request.getParameter("fullName");
			System.out.println(fullName);
			String expire = request.getParameter("expire");
			System.out.println(expire);
			String cvc = request.getParameter("cvc");
			System.out.println(cvc);
			String NTD = request.getParameter("NTD");
			System.out.println(NTD);
			String coin = request.getParameter("coin");
			System.out.println(coin);
			String bookingTime = request.getParameter("bookingTime");
			System.out.println(java.sql.Timestamp.valueOf(bookingTime));

		}
	}

}
