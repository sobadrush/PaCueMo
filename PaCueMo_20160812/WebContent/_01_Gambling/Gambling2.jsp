<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="_01_BattleSetDAO.model.*"%>
<%@ page import="_01_NBATeam.model.*"%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>

<%
	request.setCharacterEncoding("utf-8"); 
	String queryDate = request.getParameter("queryDate"); //fragment_TOP.jsp 送來的日期
	if(queryDate==null){
		queryDate= (String) request.getAttribute("queryDate");
	}
	pageContext.setAttribute("queryDate", queryDate);

	
	String queryTeamName = null;
	if (request.getAttribute("queryTeamName") != null)// 第一次名稱查詢時：由Form輸入查詢隊名傳到servlet，再由Servlet將查詢的隊名夾帶在request中傳回本JSP
	{
		queryTeamName = (String) request.getAttribute("queryTeamName");

	} else {// 第一次之後的查詢： queryTeamName → 由 page2.file 的QueryString傳送回本JSP，由於是以QueryString傳送，所以request.getAttribute("queryTeamName")會為null

		queryTeamName = request.getParameter("queryTeamName");
	}
	pageContext.setAttribute("queryTeamName", queryTeamName);//將get到的 queryTeamName 放入 pageContext (pageScope)

	
	List<Map<String, NBATeamVO>> list = null;
	if (queryTeamName == null)// 沒按過 依隊名查詢 → 就依日期查詢
	{	
		BattleSetService battleSetSvc = new BattleSetService();
		list = battleSetSvc.getLogoURLs(queryDate);
	}
	else// 按過 依隊名查詢
	{
		BattleSetService battleSetSvc = new BattleSetService();
		list = battleSetSvc.getSetsByName(queryTeamName);
	}

	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NBA 運彩專區</title>
<style>
@import url('${pageContext.request.contextPath}/_01_Gambling/css/Gambling.css');

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> <!-- GOOGLE jQuery CDN -->
<script>!window.jQuery && document.write("<script src='JS/jquery-3.1.0.min.js'><\/script>");</script><!-- 若CDN失效時 -->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">

<!-- ===============月曆============== -->
<!-- <script src="//code.jquery.com/jquery.min.js"></script> -->
<!-- ===============smart Alert============== -->
<link href="css/jquery.alertable.css" rel="stylesheet">
<script src="JS/high_class_alert/jquery.alertable.js"></script>
<script src='JS/high_class_alert/velocity.min.js'></script>
<script src='JS/high_class_alert/velocity.ui.min.js'></script>

<script>
	window.onload = function()
	{
		if (document.addEventListener)/*若瀏覽器有支援 addEventListener*/
		{
			document.getElementById("ff1").addEventListener("click", playRule, false);/*true: capture phase ; false : bubbling phase*/
		}
		else if (document.attachEvent)/*IE*/
		{
			document.getElementById("ff1").attachEvent("onclick", playRule);/* addachEvent 預設為 bubbling phase*/
		}
	}

	function playRule()
	{
		//高級Alert
		$.alertable.alert("※請先購買本網站代幣，\n之後選擇您支持的隊伍下注，\n系統將依下注總額計算賠率派發獎金！", { show : function()
		{
			$(this.overlay).velocity('transition.fadeIn');
			$(this.modal).velocity('transition.flipBounceYIn');
		}, hide : function()
		{
			$(this.overlay).velocity('transition.fadeOut');
			$(this.modal).velocity('transition.perspectiveUpOut');
		} });

		//alert("請先購買本網站代幣，\n之後選擇您支持的隊伍下注，\n系統將依下注總額計算賠率派發獎金");
		//window.open("", "運彩玩法", config='toolbar=no,height=500,width=500') ;
	}

	/* 月曆  */
	$(document).ready(function()
	{
		var date = new Date("2015-01-01");
		var currentMonth = date.getMonth();
		var currentDate = date.getDate();
		var currentYear = date.getFullYear();

		$('#datepicker').datepicker({ minDate : new Date(currentYear, currentMonth, currentDate), dateFormat : 'yy-mm-dd' });
	});
</script>

</head>
<body>
	<jsp:include page="/_99_fragmentPage/fragment_TOP.jsp" />
	<!-- ======================================================================================== -->
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"></div>
			<div class="col-md-2"></div>
		</div>
	</div>

	<div style="float: left;">
		<!-- 左方功能列表 -->
		<table class="table2">
			<tr>
				<td bgcolor="white"><img id="nbalogo" alt="nbalogo" src="<%=request.getContextPath()%>/image/NBA_logo.jpg"></td>
			</tr>
			<tr>
				<td id="ff1"><strong> 運彩玩法 </strong></td>
			</tr>
			<tr>
				<td><strong> 購買代幣 </strong></td>
			</tr>
			<tr>
				<td><strong> 熱門賽事 </strong></td>
			</tr>
			<tr>
				<td><strong> 下注紀錄 </strong></td>
			</tr>
		</table>
	</div>


	<div align="right" style="float: right; height: 200px; width: 350px;">
		<table class="table_2">
			<tr>
				<!-- 隊伍名稱搜尋 -->
				<td align="right"><c:forEach var="err" items="${errorMsgs}">
						<font color="red">${err}</font>
					</c:forEach></td>
				<td align="right" valign="middle">
					<form id="search" ACTION="<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do" METHOD="POST">
						<input type="text" name="teamName" id="s" value="搜尋隊伍.." onfocus="if(this.value == '搜尋隊伍..') {this.value = '';}" onblur="if (this.value == '') {this.value = '搜尋隊伍';}" /> <input type="submit" value="" id="r" /> <input type="hidden" name="queryDate" value="${queryDate}"> <input type="hidden" name="action" value="queryByName">
					</form>
				</td>
			</tr>
			<tr>
				<!-- 餘額 -->
				<td align="right" valign="top">餘額錯誤(TBD)</td>
				<td align="right"><img width="30" alt="您的代幣" src="<%=request.getContextPath()%>/image/money.jpg"> <input type="text" readonly="readonly" style="width: 100px; text-align: right;"></td>
			</tr>
			<tr>
				<!-- 日期搜尋 -->
				<td align="right" valign="top">日期錯誤(TBD)</td>
				<td align="right" valign="middle">
<%--  					<form id="dateForm" ACTION="<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do" METHOD="POST"> --> --%>
<!--  						<input type="submit" value="日期搜尋" align="middle"> <input type="text" name="datepicker" id="datepicker" placeholder="輸入日期搜尋" style="width: 100px;"> <input type="hidden" name="action" value="queryByDate">  -->
<!--  					</form>  -->
					<form id="dateForm" METHOD="POST">
						<input type="submit" value="日期搜尋" align="middle"> <input type="text" name="datepicker" id="datepicker" placeholder="輸入日期搜尋" style="width: 100px;"> <input type="hidden" name="action" value="queryByDate">
					</form>
				</td>
			</tr>
		</table>

	</div>

	<!-- ======================================================================================== -->

	<p />
	<p />
	<center>
		<fieldset>
			<legend>
				<font color="blue" face="微軟正黑體">本日對戰</font>
			</legend>

			<table class="table_1">
				<%@ include file="/_01_Gambling/page1.file"%>
				<c:forEach var="battleSetVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'>
						<td><img class="imgTeam" alt="home" src="<%=request.getContextPath()%>${battleSetVO['home'].teamLogoURL}"></td>
						<td><img class="imgVS" alt="vs.png" src="<%=request.getContextPath()%>/image/VS2.png"></td>
						<td><img class="imgTeam" alt="away" src="<%=request.getContextPath()%>${battleSetVO['away'].teamLogoURL}"></td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</fieldset>
	</center>
	
	<script type="text/javascript">

		$(function()//ready事件
		{
			$('#dateForm input:nth-child(1)').click(function() //L179 , submit 按鈕
			{
				//alert($('#datepicker').val());
				var inputDate = $('#datepicker').val();//取得輸入值
				
				
				$.get("<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do",
				      { 'action':'queryByDate','datepicker':inputDate } , function(data){
				      
				    	  //console.log(data);//??? why console 消失?*****************************************why?????
				    	  alert("123");
				    	  alert(data);
// 				    	  var jsonObj = JSON.parse(data);
// 				    	  alert(jsonObj);
// 				    	  console.log(jsonObj);
				    	  //console.log(data.away);
			 	})
				
				
			})
				
			
			
			
			
			
		})
	
	
	</script>
	
	
</body>
</html>



