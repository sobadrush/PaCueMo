<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>  <%-- for 【Struts】 --%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="_01_BattleSetDAO.model.*"%>
<%@ page import="_01_NBATeam.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>NBA運彩</title>
	  <style>
		@import url('${pageContext.request.contextPath}/_99_fragmentPage/css/fragmentTop.css');
		
	 	/*超連結不變色*/
		a{
			text-decoration:none;
			color:black;
		}  
			a:HOVER
			{
				color:red;
			}
	  </style>
	  <script>

	  </script>
	</head>
	<body>	
	<!-- ---------【begin 取得今天日期】-------- -->
	<%
		java.sql.Date dd = new java.sql.Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatDate = sdf.format(dd);
	%>
	<c:if test="${empty queryDate}">
		<c:set var="queryDate" value="<%=formatDate%>"/>
	</c:if>
	今天日期：${queryDate}
	<!-- ---------【end 取得今天日期】-------- -->
	
		<center>
			<table class="table1">
				<tr height="30" class="tr_row1">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><font color="white" face="微軟正黑體" size="5"><strong> 登  入 </strong></font></td>
					<td><font color="white" face="微軟正黑體" size="4"><strong> 我的球員卡 </strong></font></td>
				</tr>
				<tr height="20" class="tr_row2">
					<td colspan="2" rowspan="2" align="left"><img alt="logo" src="<%=request.getContextPath()%>/image/PaCueMO_LOGO.PNG"></td>
<!-- 				<td>表格欄位</td> -->
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr height="20" class="tr_row3">
<!-- 				<td  colspan="2">表格欄位</td> -->
<!-- 				<td>表格欄位</td> -->
					<td class="td_pageLink"><strong> 關於我們 </strong></td>
					<td class="td_pageLink"><strong> 約 戰 </strong></td>
					<td class="td_pageLink"><strong> 球 團 </strong></td>
					<td class="td_pageLink"><strong> 隊 伍 </strong></td>
					<td class="td_pageLink"><strong><a href="<%=request.getContextPath()%>/_01_Gambling/Gambling.jsp?queryDate=${queryDate}"> 運 彩 </a></strong></td>
					<td class="td_pageLink"><strong> 場 地 </strong></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</center>
	</body>
</html>




