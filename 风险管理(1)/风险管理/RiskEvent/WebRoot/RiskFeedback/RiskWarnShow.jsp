<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>风险预警查询主页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
    <style>
    .warnshow{
	width:100%;
	height:inherit;
	overflow:auto;
	border:2px solid #eff0f1;}
	.partshow{
	margin:10px 0px 10px 10px;
	width:90%;
	height:160px;
	float:center;
	border:1px solid #818181;}
	.warntitle{
	line-height:35px;
	width:75%;
	height:35px;
	float:left;
	text-align:center;
	font-size:16px;
	font-weight:bolder;
	color:#FF0000;}   
	.warnlink{
	line-height:35px;
	height:35px;
	font-size:13px;
	color:#818181;}
	.warnlist{
	width:100%;
	height:75%;
	border-top:1px solid #818181;
	margin-top:5px;}
	.tableshow{
	margin-top:5px;
	width:100%;} 
    .tableshow thead{
	font-size:13px;
	font-weight:bolder;
	color:#333333; 
	}
	.tableshow thead tr td{
	height:25px;
    border-bottom:1px solid #818181; 
	}
	 .tableshow tbody {	 
	font-size:13px;
	color:#333333;}
	 .tableshow tbody tr td{
	 border-bottom:1px dotted #818181;
	height:21px;
	}
    </style>
</head>
  
 <body style="font-size:12px; margin-top:5px;">
  <form id="riskWarn" name="riskWarn" method="post" action="riskFeedback/riskWarnAction" >
   <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险事件预警</span></div></div>
      <div class="warnshow" align="center">
  	<s:iterator value="riskStrategy" id="rs">
  <div class="partshow" align="center"> 
  <img style="float:left" src="/RiskEvent/images/colors/<s:property value="#rs.stragColor"/>.png"/>
 <span class="warntitle" style="color:#<s:property value='#rs.stragColor'/>" ><s:property value="#rs.stragStatue"/></span>
 <a class="warnlink" href="javascript:void(0)" onclick="openTab('riskFeedback/riskWarnQuery?strategyId=<s:property value="#rs.riskStrId"/>&current_pagenum=1','风险预警查看')">更多</a>
 <div class="warnlist">
 <table class="tableshow" cellpadding="0" cellspacing="0" align="center">
<thead>
 <tr>
  <td width="20%" align="center">风险编号</td>
  <td width="20%" align="center">风险名称</td>
  <td width="20%" align="center">录入部门</td>
  <td width="20%" align="center">计划时间</td>
  <td width="20%" align="center">录入时间</td>
  
  </tr>
  </thead>
  <tbody>
  <%int n=1,i=0; %>
  <s:iterator value="riskWarnList" id="listinfo">
  <s:if test="#rs.riskStrId==#listinfo.stragyId ">
  <% 
  i++;
  if((n++)<6){ %>
  <tr>
  <td width="20%" align="center" title="<s:property value="#listinfo.id.reId"/>" ><a href="javascript:void(0)" onclick="openTab('riskInput/REIQReadAction?reId=<s:property value='#listinfo.id.reId'/>&backFlag=reply','风险应对-<s:property value='#listinfo.id.reEventname'/>')"><s:property value="#listinfo.id.reId"/></a></td>
  <td width="20%" align="center" title="<s:property value="#listinfo.id.riskName"/>"><s:property value="#listinfo.id.riskName"/></td>
  <td width="20%" align="center" title="<s:property value="#listinfo.id.depName"/>"><s:property value="#listinfo.id.depName"/></td>
  <td width="20%" align="center"><s:property value="#listinfo.id.rmPlandate"/></td>
  <td width="20%" align="center"><s:property value="#listinfo.id.reDate"/></td>
  
  </tr>
  <%} %>
  </s:if>
  </s:iterator>
  </tbody>
  </table>
  </div>
  <div align="right">共<%=i%>条风险事件</div>
  </div>
  </s:iterator> 
  </div>
<div class="MainDiv">
</div>
</form>

  </body>
  
  <script type="text/javascript">
  	function openTab(url,tabname) {
  		window.parent.openTab(url,tabname);
  	};
  </script>
  
</html>
