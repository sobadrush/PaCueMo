<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%> <%--use JSTL Standard Syntax--%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>  <%-- for 【Struts】 --%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	  <style>
		  table
		  {
			  border-collapse: collapse;
			  border-spacing: 0;
			  text-align:center;
/* 		  	border-collapse: collapse; */
		  }
 		  td
		  {
		  	width:300px;
		  }
		  .tr_row1
		  {
		  	background:#444444;
		  }
		   .tr_row2
		  {
		  	background:#888888;
		  }
		   .tr_row3
		  {
		  	background:#888888;
		  } 
		  .td_pageLink
		  {
		  	background:#5599FF;
		  	border:1px blue solid;
		  	border-radius: 5px;
		  	color:black;
		  	font-size:18px;
		  }
		  .td_pageLink:hover {
			 background-color: #ffff99;
			 font-size:25px;
			 color:red;
			}
			
			
		  .table2
		  {
		   	  border-collapse: collapse;
			  border-spacing: 0;
			  text-align:center;
		  }	
		  	.table2 tr
		  	{
		  		height:30px;
		  	}
		    	.table2 td
		    	{
		    		font-family:"微軟正黑體";
		    		font-style:italic;
		    		width:200px;
		    		background-color:#CCEEFF;
		    		border:1px solid blue;
		   		}
		 		.table2 td:HOVER
		 		{
			 		 background-color: #FF5511;
					 font-size:25px;
					 color:Black;
		 		}
		 		
		  #nbalogo{
		  	width:150px;
		  	height:80px;
		  }
		
	  </style>
	  <script>
		  function playRule()
          {
			  alert("請先購買本網站代幣，\n之後選擇您支持的隊伍下注，\n系統將依下注總額計算賠率派發獎金");
          }
		  <%--insert JScript here--%>
		  <%--<script src=""></script>--%>
	  </script>
	</head>
	<body>
		<center>
			<table>
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
					<td colspan="2" rowspan="2" align="left"><img alt="logo" src="/PaCueMo/image/PaCueMO_LOGO.PNG"></td>
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
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 關於我們 </strong></font></td>
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 約 戰 </strong></font></td>
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 球 團 </strong></font></td>
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 隊 伍 </strong></font> </td>
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 運 彩 </strong></font> </td>
					<td class="td_pageLink"><font face="微軟正黑體"><strong> 場 地 </strong></font> </td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</center>
		
<!-- ======================================================================================== -->	
		<div style="float:left;">
			<table class="table2">
				<tr><td><img id="nbalogo" alt="nbalogo" src="/PaCueMo/image/NBA_logo.jpg"></td></tr>
				<tr><td id="ff1"  onclick="playRule()"><strong> 運彩玩法 </strong></td></tr>
				<tr><td><strong> 購買代幣 </strong></td></tr>
				<tr><td><strong> 熱門賽事 </strong></td></tr>
				<tr><td><strong> 下注紀錄 </strong></td></tr>
			</table>
		</div>
		
		
		
	</body>
</html>




