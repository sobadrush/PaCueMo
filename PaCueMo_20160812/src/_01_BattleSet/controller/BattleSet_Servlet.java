package _01_BattleSet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _01_BattleSetDAO.model.BattleSetService;
import _01_NBATeam.model.NBATeamVO;

@WebServlet("/_01_Gambling/BattleSet_Servlet.do")
public class BattleSet_Servlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		request.setCharacterEncoding("UTF-8");
//		response.setHeader("content-type", "text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.write("國國國國國國國國");
//		return;
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//---------------------------------------------------------------------------
//		action = "queryByDate";
		if ("queryByDate".equals(action))
		{
			//System.out.println("呼叫 BattleSet_Servlet : queryByDate L35");
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try
			{
				response.setHeader("content-type", "text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String queryDate = request.getParameter("datepicker");
//				String queryDate = request.getParameter("xxx");
				System.out.println(queryDate);
//				queryDate = "2015-11-06";/////////////////////////////////////////////////////////////////////

				if (queryDate == null || queryDate.trim().length() == 0)
				{
					errorMsgs.add("請輸入正確日期");
				}
				/*************************** 2.開始查詢資料 ( jQuery + Ajax : return JSON ) **********/
				BattleSetService svc = new BattleSetService();
				List<Map<String, NBATeamVO>> list = svc.getSetsByDate(queryDate);
				Gson gson = new Gson();

				String ans = gson.toJson(list);

//				StringBuffer ans = new StringBuffer();
//				for (Map<String, NBATeamVO> map : list)
//				{
//					ans.append(gson.toJson(map));
//
//				}
				System.out.println(ans);
				out.println(ans.toString());
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				/* use jQuery with Ajax */
				/*************************** 其他可能的錯誤處理 *************************************/
			}
			catch (Exception e)//---處理其他不可預期意外
			{
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("xxxxxxxxxx");
				failureView.forward(request, response);
			}

		}

		//====================================【依輸入隊名查詢】==========================================

		if ("queryByName".equals(action))
		{
			//System.out.println("呼叫 BattleSet_Servlet : queryByName L35");
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try
			{
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String queryTeamName = request.getParameter("teamName");

				if (queryTeamName.equals("搜尋隊伍.."))
				{
					errorMsgs.add("※請輸入隊名");
					System.out.println("=== ※請輸入隊名 ===  (BattleSet_Servlet.java L103)");
					request.getRequestDispatcher("/_01_Gambling/Gambling.jsp").forward(request, response);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				BattleSetService svc = new BattleSetService();

				List<Map<String, Object>> list = svc.getSetsByName(queryTeamName);
				if (list == null)
				{
					errorMsgs.add("※查無此隊伍");
					System.out.println("=== ※查無此隊伍 ===  (BattleSet_Servlet.java L114)");
					request.getRequestDispatcher("/_01_Gambling/Gambling.jsp").forward(request, response);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

//				for (Map<String, NBATeamVO> mymap : list)
//				{
//					System.out.println(mymap.get("home").getTeamName() + "  vs " + mymap.get("away").getTeamName());
//				}
				request.setAttribute("queryTeamName", queryTeamName);
				request.setAttribute("byName_list", list);
				request.getRequestDispatcher("/_01_Gambling/Gambling.jsp").forward(request, response);
				return;
				/*************************** 其他可能的錯誤處理 *************************************/
			}
			catch (Exception e)//---處理其他不可預期意外
			{
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("自訂錯誤頁面");
				failureView.forward(request, response);
			}

		}
	}

}
