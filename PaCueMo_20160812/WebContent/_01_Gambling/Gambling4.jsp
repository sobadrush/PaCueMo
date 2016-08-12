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
 
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>NBA 運彩專區</title>
  	<style>
  		@import url('${pageContext.request.contextPath}/_01_Gambling/css/Gambling.css');
	</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> <!-- GOOGLE jQuery CDN -->
<script>!window.jQuery && document.write("<script src='js/jquery-3.1.0.min.js'><\/script>");</script><!-- 若CDN失效時 -->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<!-- ===============smart Alert============== -->
<link   rel="stylesheet" href="css/jquery.alertable.css" >
<script src="js/high_class_alert/jquery.alertable.min.js"></script>
<script src='js/high_class_alert/velocity.min.js'></script>
<script src='js/high_class_alert/velocity.ui.min.js'></script>
<!-- ****************** 【BootStrap】******************** -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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

	/* 月曆-2  */
	$(function() {

	    $( "#datepicker-2" ).datepicker({
	    	  dateFormat: 'yy-mm-dd'
	    });
	});
</script>

</head>
<body>
	<jsp:include page="/_99_fragmentPage/fragment_TOP.jsp" />
	<!-- ======================================================================================== -->
	<div class="container">
		<div class="row">
		    <div class="col-md-3">
		    	<img align="middle" class="img-rounded" id="nbalogo" alt="nbalogo" src="<%=request.getContextPath()%>/image/NBA_logo.jpg">

			    <ul class="nav nav-pills nav-stacked">
					  <li id="ff1" role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-chevron-right">  運彩玩法</span></a></li>
					  <li role="presentation"><a href="#"><span class="glyphicon glyphicon-chevron-right">  購買代幣</span></a></li>
					  <li role="presentation"><a href="#"><span class="glyphicon glyphicon-chevron-right">  熱門賽事</span></a></li>
					  <li role="presentation"><a href="#"><span class="glyphicon glyphicon-chevron-right">  下注紀錄</span></a></li>
					  <li role="presentation">
						  	<c:forEach var="err" items="${errorMsgs}">
								<font color="red">${err}</font>
							</c:forEach>
						  	<form id="search" ACTION="<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do" METHOD="POST">
								<input type="text" name="teamName" id="s" value="搜尋隊伍.." onfocus="if(this.value == '搜尋隊伍..') {this.value = '';}" onblur="if (this.value == '') {this.value = '搜尋隊伍';}" /> <input type="submit" value="" id="r" /> <input type="hidden" name="queryDate" value="${queryDate}"> <input type="hidden" name="action" value="queryByName">
							</form>
					  <li role="presentation">
						  	<img width="30" alt="您的代幣" src="<%=request.getContextPath()%>/image/money.jpg"> <input type="text" readonly="readonly" style="width: 100px; text-align: right;">
					  </li>
					  <li id="dateQuery" role="presentation" style="margin-top:20px;">
					 	 <button type="button" class="btn btn-info btn-block glyphicon glyphicon-search"> 日期查詢 </button>
					  	 <div id="datepicker-2"  style="margin-left:10px;"></div>
					  </li>
				</ul>


			</div>
			<div class="col-md-9">
		    	<div id="myJumbo" class="jumbotron">
					<div class="page-header">
							<center><%@ include file="/_01_Gambling/page1.file"%></center>
							<table class="table table-hover">
								<c:forEach var="battleSetVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle'>
										<td><img width="150" class="img-rounded" alt="home" src="<%=request.getContextPath()%>${battleSetVO['home'].teamLogoURL}"></td>
										<td><img width="100" class="img-circle"  alt="vs.png" src="<%=request.getContextPath()%>/image/VS2.png"></td>
										<td><img width="150" class="img-rounded" alt="away" src="<%=request.getContextPath()%>${battleSetVO['away'].teamLogoURL}"></td>
									</tr>
								</c:forEach>
							</table>
							<center><%@ include file="page2.file"%></center>


<!-- 							<div id="qByDate" class="page-header"></div> -->

							<!-- Site footer -->
							<p>&copy; NBA.com</p>

					</div>

				</div>
			</div>

		</div>


	</div>


<!-- ================================================================================= -->


	<script type="text/javascript">

		$(function()//ready事件
		{

// 		 	$('.table~div:first').click(function(){ // 若要選同層的第二個 $('.table~div:first').next() → 加next
// 			   		alert("ABC");
// 			})

// 			$('#myJumbo').on('click','>div',function()  // 將事件綁到上層
// 			{
// 				alert('123')
// 			})

			$('#dateQuery button:eq(0)').click(function() //L179 , submit 按鈕
			{
				var inputDate = $('#datepicker-2').val();//取得輸入值
				//alert(inputDate);
				
// 				var formData = new FormData();
// 				formData.append('action','queryByDate');
// 				formData.append('datepicker',inputDate);
// 				alert(formData);

				$.post("<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do",
				       { 'action':'queryByDate','datepicker':inputDate }, 
				       
				       function(data)
				       {
							// 1.先刪除舊內容
							$('#myJumbo>div').remove();
							
				    	   
				    	   //  2.建立元素：
			    		   var myDivHeader = $('<div></div>').addClass('page-header');
						   var myTable     = $('<table></table>').addClass('table table-hover');
						   var myBody      = $('<tbody></tbody>');
				    	   
						   
				    	   //alert(data);
				    	   $.each(data , function ( index , obj ){
								
// 				    		   console.log(index);
// 				    		   console.log(obj);
// 				    		   console.log(obj.away);
// 				    		   console.log(obj.home);

				    		   var myTr   = $('<tr></tr>');
				    		   var myTd1  = $('<td></td>');
				    		   var myTd2  = $('<td></td>');
				    		   var myTdVS = $('<td></td>');  
				    		   
				    		   var attr_1  = {  'src'   : ".." + obj.home.teamLogoURL ,
												'width' : 150
				    		   }
				    		   var attr_2  = {  'src'   : ".." + obj.away.teamLogoURL ,
												'width' : 150
				    		   }
				    		   var attr_VS = {  'src'   : ".." + "/image/VS2.png" ,
												'width' : 100
				    		   }
				    		   
				    		   var myImg1  = $('<img></img>').attr(attr_1)
				    		   								 /*attr('src' , ".." + obj.home.teamLogoURL)
				    		   								 .attr('width',150)*/
				    		   								 .addClass('img-rounded');
				    		   								
  							   var myImg2  = $('<img></img>').attr(attr_2)
  							   								 /*attr('src' , ".." + obj.away.teamLogoURL)
  															 .attr('width',150)*/
  															 .addClass('img-rounded');
  															
						       var myImgVS = $('<img></img>').attr(attr_VS)
						       								 /*attr('src' , ".." + "/image/VS2.png")
															 .attr('width',100)*/
															 .addClass('img-circle');							
								
							   myTd1.append(  myImg1 );
							   myTd2.append(  myImg2 );
							   myTdVS.append( myImgVS );
							   myTr.append( [ myTd1 , myTdVS , myTd2 ] );								
							   myBody.append(myTr);	
						   })
						   myTable.append(myBody);
				    	   myDivHeader.append(myTable);
				    	   $('#myJumbo').append(myDivHeader);
					   } , 
					   
				'json'/*$.post 的第4個參數：指定回傳的接收格式為JSON*/ );
				
				
				
				
				
				/*========================================= */
				/*============== 依日期查詢================ */
				/*========================================= */

				//alert($('#datepicker-2').val());
// 				var inputDate = $('#datepicker-2').val();//取得輸入值
<%-- 				$.post("<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do", --%>
// 				      { 'action':'queryByDate','datepicker':inputDate } , function(data){

// 				    	  //alert("123");
// 				    	  //alert(data);

// 				    	  var myDivJumbo = document.getElementById("myJumbo"); //L158
// 				    	  myDivJumbo.className="jumbotron";
// 				    	  // 1. 先刪除舊的內容
// 				    	  if(myDivJumbo.hasChildNodes())
// 				    	  {
// 				    		  myDivJumbo.removeChild( myDivJumbo.firstChild );
// 				    		  myDivJumbo.removeChild( myDivJumbo.firstChild );//???
// 				    	  }

// 				    	  // 2. 再開始建立元素
// 				    	  var myDivHeader = document.createElement("div");
// 					      myDivHeader.className = "page-header";

// 						  var myTable = document.createElement("table");
//                           myTable.className="table table-hover";
// //                         myTable.setAttribute("class" ,"table table-hover" );

//                           var myBody = document.createElement("tbody");

// 				    	  var jsonObj = JSON.parse(data);
// 				    	  for(var i=0 , max=jsonObj.length ; i< max ; i++)
// 				    	  {
// 				    		  console.log(jsonObj[i].home.teamName + " vs " + jsonObj[i].away.teamName);
// 				    		  console.log(jsonObj[i].home.teamLogoURL + " vs " + jsonObj[i].away.teamLogoURL);

// 				    		  var myTr  = document.createElement("tr");

// 				    		  var myTd1  = document.createElement("td");
// 				    		  var myTd2  = document.createElement("td");
// 				    		  var myTdVS = document.createElement("td");

// 				    		  var myImg1 = document.createElement("img");
// 				    		  myImg1.setAttribute("src" , ".." + jsonObj[i].home.teamLogoURL);
// 				    		  myImg1.setAttribute("width" , 150);
// 				    		  myImg1.className = "img-rounded";

// 				    		  var myImg2 = document.createElement("img");
// 				    		  myImg2.setAttribute("src" , ".." + jsonObj[i].away.teamLogoURL);
// 				    		  myImg2.setAttribute("width" , 150);
// 				    		  myImg2.className = "img-rounded";

// 				    		  var myImgVS = document.createElement("img");
// 				    		  myImgVS.setAttribute("src" , ".." + "/image/VS2.png");
// 				    		  myImgVS.setAttribute("width" , 100);
// 				    		  myImgVS.className = "img-circle";

// 				    		  myTd1.appendChild(myImg1);
// 				    		  myTd2.appendChild(myImg2);
// 				    		  myTdVS.appendChild(myImgVS);

// 				    		  myTr.appendChild(myTd1);
// 				    		  myTr.appendChild(myTdVS);
// 				    		  myTr.appendChild(myTd2);

// 				    		  myBody.appendChild(myTr);

// 				    		  myTable.appendChild(myBody);
// 				    	  }

// 				    	  myDivHeader.appendChild(myTable);

// 				    	  var myP = document.createElement("p");//<p>&copy; NBA.com</p>
// 				    	  myP.innerHTML="&copy; NBA.com";
// 				    	  myDivHeader.appendChild(myP);

// 				    	  myDivJumbo.appendChild(myDivHeader);

// 			 	})

			})

		})


	</script>


</body>
</html>
