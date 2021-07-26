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
    
    <title>部门警戒查询</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
    <script language="javascript" src="/RiskEvent/js/funcJS.js"></script>
  
  </head>
  
<body style="font-size:12px; margin-top:5px;" >
<form name="depCaution" id="depCaution" method="post" action="riskFeedback/depCautionAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>部门警戒</span></div></div>
<div class="queryDiv">
<div style="display:;text-align: center">
<ul style="list-style-type:none;width:inherit;margin-top:5px;">
<li style="float:left;margin-left:20px;font-size:14px;">部门名称：<s:select name="riskDeps" theme="simple" list="alldepList" listValue="depName" listKey="depId" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>
<li style="float:left;margin-left:5px;">
<input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />
</li>
</ul>
</div>
</div>
<p:page pageTitle="部门名称;风险事件编号;警戒时间"  pageVaule="department.depName;riskEvent;dwTime" id="mytable" dataProvider="depWarnList"
  			actionUrl="riskFeedback/depCautionAction"
  			pageNum="10"
  			menu="true"
  			menuAlign="left"
  			deleteUsed="true" deleteLink="riskFeedback/depCautionMultiDelAction"
  			checkUsed="true" checkValue="dwId"		
 ></p:page>
  
  
  </form>
  <script type="text/javascript">

function query()
{
	depCaution.action="riskFeedback/depCautionQueryAction?current_pagenum=1";
	depCaution.submit();
	
}
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		depCaution.action="riskFeedback/depCautionQueryAction?current_pagenum=1";
		depCaution.submit();
		
	}
}
  </script>
  </body>
</html>
