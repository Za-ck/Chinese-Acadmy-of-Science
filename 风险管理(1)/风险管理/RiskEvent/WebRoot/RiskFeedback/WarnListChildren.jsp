<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="pageV" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>风险预警查看</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
  </head>  
<body style="font-size:12px; margin-top:5px;">
<form name="warnListChildren" id="warnListChildren" method="post" action="">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险预警查看</span></div></div>
             <p:page pageTitle="状态;风险事件编号;风险名称;录入部门;计划时间;录入时间"  pageVaule="value;id.reId;id.riskName;id.depName;id.rmPlandate;id.reDate" id="mytable" dataProvider="riskWarnListChildren"
             actionUrl="riskFeedback/riskWarnQuery"
             pageNum="10"
             menu="true" 
             menuAlign="left" 
             dcLineUsed="true"
             dcLink="riskInput/REIQReadAction"       
             checkValue="id.reId"          
             ></p:page>
</form>

</body>
</html>
