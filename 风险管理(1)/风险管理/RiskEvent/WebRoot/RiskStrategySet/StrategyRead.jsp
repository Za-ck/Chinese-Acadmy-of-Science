<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>风险策略信息查看</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script src="/RiskEvent/js/jquery-1.7.1.min.js"></script>
    <script src="/RiskEvent/js/colorpick/jquery.colorpicker.js"></script>
     <style>
    .add{
    cursor:pointer;
	margin-top:10px;
	margin-left:10px;
	width:90px;
	height:45px;
	color:#ffffff;
	background-color:#0066FF;
	text-align:center;
	float:left;}
	 .add span{
	width:100%;
	height:45px;
	color:#ffffff;
	line-height:45px;
	font-weight:bolder;
	font-size:12px;}
	 .delete{
	 cursor:pointer;
	margin-top:10px;
	margin-left:10px;
	width:90px;
	height:45px;
	color:#ffffff;
	background-color:#CC0000;
	text-align:center;
	float:left;}
	 .delete span{
	width:100%;
	height:45px;
	color:#ffffff;
	line-height:45px;
	font-weight:bolder;
	font-size:12px;}
	.strategName{
	width:100%;
	margin-top:5px;
	margin-left:10px;
	}
	.strategName span{
	font-size:14px;
	color:#818181;
	font-weight:bolder;}
	.content{
	margin-top:45px;
	width:100%;
	}
	.submitAdd{
	cursor:pointer;
	margin-top:5px;
	height:35px;
	text-align:center;}
	.submitAdd span{
	background-color:#CCCCCC;
	color:#FFFFFF;
	line-height:35px;
	width:70px;
	height:30px;
	border:1px solid #81818;}
	
	.listinfo{
	padding:5px;
	border:1px solid #81818;
	width:650px;
	font-size:14px;
	margin-left:10px;
	height:35px;
	}
	.listinfo:HOVER{
	border:2px solid #ff6600;}
	.listinfo span{
	margin-left:5px;
	line-height:35px;}
	.updatech{
	text-align:center;
	margin-right:5px;
	margin-left:35px;
	background-color:#FF9900;
	color:#FFFFFF;
	line-height:35px;
	width:50px;
	height:30px;
	border:1px solid #81818;
	cursor:pointer;
	}
	.deletech{
	margin-right:0px;
	text-align:center;
	margin-left:5px;
	background-color:#CC3300;
	color:#FFFFFF;
	line-height:35px;
	width:50px;
	height:30px;
	border:1px solid #81818;
	cursor:pointer;
	}
	.desp{
	font-size: 14px;
	font-weight: bolder;
	color: #818181;}
	.values{
	width:35px;
	font-size: 14px;
	font-weight: bold;
	color: #1281d3;}
    </style>

  </head>
  
  <body>
    <form id="strategyadd" name="strategyadd" method="post" action="RiskStrategySet/updateOrAddSetinfo" >
   <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险策略信息查看</span></div></div>
  <div class="strategName"><span>风险策略名称:</span>
     <label>
     <input disabled="disabled" type="text" name="strategyName" id="strategyName" value="<s:property value='strategyName'/>">
     <input type="hidden" id="strategyId" name="strategyId" value="<s:property value='strategyId'/>"/>
     <input type="hidden" id="updateFlag" name="updateFlag" value="<s:property value='updateFlag'/>"/>
     </label>
     <br> 
   </div>
   <div class="strategName"><span>部门名称:</span>
     <label>
     <input disabled="disabled" type="text" name="depName" id="depName" value="<s:property value='depName'/>">
     </label>
     <br> 
   </div>
   
   <div id="contentlist" class="content"> 
 <s:iterator value="riskStrategyList">
  <div id="listinfo<s:property value='riskStrId'/>" class="listinfo">
  <span class='desp'>策略状态:</span><span class='values'><s:property value='stragStatue'/></span> 
  <span class='desp'>策略最小值:</span>
  <span class='values'><s:property value='stragValue'/></span> 
  <span class='desp'>策略最大值:</span>
  <span class='values'><s:property value='remark'/></span> 
  <span class='desp'>颜色:</span> 
  <span style="border: 1px solid #818181;background-color:<s:property value='stragColor'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
  
  </div>
  </s:iterator>
 
   </div>
  </body>
</html>
