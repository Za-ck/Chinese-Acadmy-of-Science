<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="p"  uri="pageV"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Strategy.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
  </head>
  
  <body style="font-size:12px; margin-top:5px;">
  <form id="strategy" name="strategy" method="post" action="RiskStrategySet/riskStrategyAction" >
 <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险预警策略信息列表</span></div></div>
  <p:page pageTitle="风险事件策略名"  pageVaule="strategyName" id="mytable" dataProvider="strategyList"
             actionUrl="RiskStrategySet/riskStrategyAction"
             pageNum="10"
             menu="true" 
             menuAlign="left" 
             addUsed="true" addLink="RiskStrategySet/AddStrategyInfo?updateFlag=add"
             deleteUsed="true" deleteLink="RiskStrategySet/riskStrategyMultiDeleteAction"
             checkUsed="true" checkValue="strategyId"
             updateUsed="true" updateLink="RiskStrategySet/riskStrategyInfAction"    
             ></p:page>

</form>
  </body>
</html>
