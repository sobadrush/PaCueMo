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


	List<? extends Map<String, ?>> list = null;//////////////////////////////////////////////
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
<!-- ****************** 【credit card】******************** -->
<script src="js/credit_Card/jquery.card.js"></script>

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
					  <li id="ff1" role="presentation"><a href="#"><span class="glyphicon glyphicon-chevron-right">  運彩玩法</span></a></li>
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
					  <li id="dateQuery" role="presentation" style="margin-top:20px;"><!-- 日期查詢 -->
					  	 <div id="datepicker-2"  style="margin-left:10px;"></div>
					  </li>
				</ul>


			</div>
			<div class="col-md-9">
		    	<div id="myJumbo" class="jumbotron">
		    	
					<div class="page-header">
							<h2><strong style="font-family:微軟正黑體;font-weight:bolder;">${queryDate}</strong></h2>
<%-- 						<h2><strong style="font-family:微軟正黑體;font-weight:bolder;">${queryTeamName}</strong></h2> --%>
					</div>
					
					<div id="setsTable">
						<div><!-- tmpDiv -->
							<%@ include file="/_01_Gambling/page1.file"%>
							<table class="table"><!-- Bootstrap : class="table-hover" -->
								<c:forEach var="battleSetVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex + rowsPerPage-1%>">
		
									<tr align='center' valign='middle'>
										<td><img width="150" class="img-rounded" alt="home"   src="<%=request.getContextPath()%>${battleSetVO['home'].teamLogoURL}"></td>
										<td><img width="100" class="img-circle"  alt="vs.png" src="<%=request.getContextPath()%>/image/VS2.png"></td>
										<td><img width="150" class="img-rounded" alt="away"   src="<%=request.getContextPath()%>${battleSetVO['away'].teamLogoURL}"></td>
									</tr>
			
									<tr align='center' valign='middle'>
										<td><h4 style="font-family:微軟正黑體;font-weight:bolder;">${battleSetVO['home'].teamName}</h4></td>
										<td><Strong class='glyphicon glyphicon-time' style="padding-right:5px;">${battleSetVO['battleTime']}</Strong></td>
										<td><h4 style="font-family:微軟正黑體;font-weight:bolder;">${battleSetVO['away'].teamName}</h4></td>
									</tr>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>
						</div>
					</div>


					<!-- Site footer -->
					<p><strong>&copy; NBA.com</strong></p>
			
				</div>
			</div>

		</div>


	</div>

		<!-- ====================================================================== -->
		<!-- ====================【 購買點數 - 信用卡 dialog 】==================== -->
		<!-- ====================================================================== -->
		<div id="dialog-form" title="購買點數">
	            <div class="card-wrapper"></div>
	            <form id="credit_card" action="">
	                <!-- <fieldset style="padding: 0;border: 0;margin-top: 25px;"> -->
	                
	                  <div class="form-group">
						  <label class="control-label" for="number" style="font-family:'微軟正黑體';font-weight:bolder;">卡 號</label>
						  <input placeholder="Card number" type="text" name="number" class="form-control">
					  </div>
	                  <div class="form-group">
						  <label class="control-label" for="name" style="font-family:'微軟正黑體';font-weight:bolder;">姓 名</label>
						  <input placeholder="Full name" type="text" name="name" class="form-control">
					  </div>
	                  <div class="form-group">
						  <label class="control-label" for="expiry" style="font-family:'微軟正黑體';font-weight:bolder;">期 限</label>
						  <input placeholder="MM/YY" type="text" name="expiry" class="form-control">
					  </div>
					  <div class="form-group">
						  <label class="control-label" for="cvc" style="font-family:'微軟正黑體';font-weight:bolder;">代 碼</label>
						  <input placeholder="CVC" type="text" name="cvc" class="form-control">
					  </div>
					 <div class="form-group col-xs-6">
						  <label class="control-label" for="NTD" style="font-family:'微軟正黑體';font-weight:bolder;">購買金額 (1 NT$ : 100 P)</label>
						  <input placeholder="購買金額(NT)" type="text" name="NTD" class="form-control">
					  </div>
					   <div class="form-group col-xs-6">
						  <label class="control-label" for="coin" style="font-family:'微軟正黑體';font-weight:bolder;">代幣數量</label>
						  <input placeholder="代幣數量" type="text" name="coin" class="form-control" readonly="readonly">
					  </div>
	                  <!-- Allow form submission with keyboard without duplicating the dialog button -->
	                  <!-- <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"> -->
	               <!--  </fieldset> -->
	            </form>
	    </div>
<!-- ================================================================================= -->

	<script src="js/js_Util.js"></script>
	<script type="text/javascript">

		$(function()//ready事件
		{	     

			$('#ff1').next().on("click", function () { /* 購買代幣 <li> , 點擊開啟信用卡dialog */
           	 myDialog.dialog("open");
        	});
			//===================【 購買點數 - 信用卡 】======================		
			
			/* keyin 台幣 => 轉代幣  */
			$("input[placeholder='購買金額(NT)']").keyup(function(){ 
					$("input[placeholder='代幣數量']").val($(this).val() * 100 /* 代幣比值 */);
			})
				
			 var myDialog, form;

            myDialog = $("#dialog-form").dialog({
                autoOpen: false,
                height: 700,
                width: 500,
                modal: true,
                buttons:[
	                         {
	                        	 	 text  : "確認購買",
	                        	 	'class' : "btn btn-primary",
	                        	 	 click : function (){
	                        	 			
	                        	 		   //alert($(this).prop('tagName') +" 確認" );
	                        	 		    var cardNum  = $("input[placeholder='Card number']").val();
	                        	 		    var fullName = $("input[placeholder='Full name']").val();
	                        	 		    var expire   = $("input[placeholder='MM/YY']").val();
	                        	 		    var cvc      = $("input[placeholder='CVC']").val();
	                        	 		    var ntd      = $("input[placeholder='購買金額(NT)']").val();
	                        	 		    var coin     = $("input[placeholder='代幣數量']").val();

	                        	 			//======================================================
	                        	 			//==============【傳送信用卡資訊到servlet】=============
	                        	 			//======================================================	
	                        	 			$.ajax({
	                        	 				"type" : "post",
	                        	 				"url"  : "<%=request.getContextPath()%>/_01_Gambling/GoodsOrder_Servlet.do",
	                        	 				"data" : { 
	                        	 							'action'    :   'buyCoins'   , 
	                        	 						   'cardNum'    :   cardNum    ,
	                        	 						   'fullName'   :   fullName   ,
	                        	 						   'expire'     :   expire     ,
	                        	 						   'cvc'        :   cvc        ,
	                        	 						   'NTD'        :   ntd        ,
	                        	 						   'coin'        :  coin        ,
	                        	 						   'bookingTime' :  timeStamp()   //下訂時間
	                        	 				},
	                    
	                        	 				"success" : function(){
	                        	 					alert('hello');
	                        	 				}
	                        	 			})
	                        	 				
	                        	 		
	                        	 			//======================================================
	                                    myDialog.dialog("close");/*關閉 dialog*/
									 }
	                         }  ,
	                         {
	                        	 	 text  : "取消",
	                        	 	'class' : "btn btn-primary",
	                        	 	 click : function (){
	                        	 			 //alert($(this).prop('tagName')+"cancel");
	                                     myDialog.dialog("close");/*關閉 dialog*/
									 }
                         		}
                         
               ] ,

                close: function () {
                    form[0].reset();
                }
            });

            form = myDialog.find("form").on("submit", function (event) {
                event.preventDefault();
                alert('Hello');
            });

 
            //------------ 信用卡 --------------
            $('#dialog-form').card({
                // a selector or DOM element for the container
                // where you want the card to appear
                container: '.card-wrapper', // *required*
                // all of the other options from above
            });
			
			//=================== 左方選單 choosed ======================
			$('ul.nav-stacked > li' ).click(function () /*ul 下的 li*/
			{
				$('li.active').removeClass();/*li的class叫 active的所有元素*/
				$(this).addClass('active');
			})
			
			//================== 對戰組合 Hover ,  on 綁定到上層(處理動態新增元素) =====================
			$('#myJumbo').on('mouseover','tr:nth-child(2n+1)', function(){
				$(this).css('background','rgba(224,224,224,1)'); 
			}).on('mouseout','tr:nth-child(2n+1)',function(){
				$(this).css('background','rgba(224,224,224,0.1)'); 
			})
			
			/* 月曆-2  */
			$(function() {
		
			    $( "#datepicker-2" ).datepicker({
			    	  dateFormat: 'yy-mm-dd',
			    	  onSelect: function () {
			    		    /*================================================= */
							/*============== 依日期查詢 jQuery ================ */
							/*================================================= */
							
							var inputDate = $('#datepicker-2').val();//取得輸入值
							//alert(inputDate);
							$('.page-header strong').text(inputDate);
							
							$.post("<%=request.getContextPath()%>/_01_Gambling/BattleSet_Servlet.do",
							       { 'action':'queryByDate','datepicker':inputDate }, 
							       
							       function(data)
							       {
									   //  1.先刪除舊內容
									   $('#setsTable > div').remove();

										
							    	   //  2.建立元素：
									   var myTable     = $('<table></table>').addClass('table');
									   var myBody      = $('<tbody></tbody>');
							    	   var tmpDiv      = $('<div></div>')
									   
							    	   //alert(data);
							    	   $.each(data , function ( index , obj ){
											
//			 				    		   console.log(index);
//			 				    		   console.log(obj);
//			 				    		   console.log(obj.away);
//			 				    		   console.log(obj.home);

										   var attr_Tr = {'align':'center','valign':'middle' }
							    		   //---------【放隊徽】----------
							    		   var myTr1  = $('<tr></tr>').attr( attr_Tr );
							    		   var myTd1  = $('<td></td>');
							    		   var myTd2  = $('<td></td>');
							    		   var myTdVS = $('<td></td>');  
							    		   //---------【放隊名】----------
							    		   var myTr2  = $('<tr></tr>').attr( attr_Tr );
							    		   
							    		   $('<td></td>').html("<h4 style='font-family:微軟正黑體;font-weight:bolder;'>" +
							    		   						 obj.home.teamName + '</h4>').appendTo(myTr2); 
							    		   
							    		   $('<td></td>').html("<Strong class='glyphicon glyphicon-time'>" +
							    		   						 obj.battleTime + '</Strong>').appendTo(myTr2);
							    		   
							    		   $('<td></td>').html("<h4 style='font-family:微軟正黑體;font-weight:bolder;'>" + 
							    		   						 obj.away.teamName + '</h4>').appendTo(myTr2);
							    		 
							    		   
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
										   myTr1.append( [ myTd1 , myTdVS , myTd2 ] );								
										   myBody.append(myTr1).append(myTr2);	
									   })
									   
									   myTable.append(myBody);
									   tmpDiv.append(myTable);
							    	   $('#setsTable').append(tmpDiv);
								   } , 
								   
							'json'/*$.post 的第4個參數：指定回傳的接收格式為JSON*/ );
			    	  }
			    });
			});



		})


	</script>


</body>
</html>
