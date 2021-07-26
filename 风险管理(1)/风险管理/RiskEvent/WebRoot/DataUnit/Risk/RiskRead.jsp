<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>风险信息查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
</head>
  
 <body class="ContentBody" >
<form action="dataUnit/RisReadAction" method="post" enctype="multipart/form-data" name="risReadForm" id="risReadForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>风险信息查看</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  <tr>
    <td class="CPanel">	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">	
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" bgcolor="#F8FCFC">
					  <tr>
					    <td nowrap align="right" width="35%">风险编号：</td>
					    <td width="65%">
					       <input disabled="disabled" name="riskIdString" type="text" class="text" style="width:154px" value="<s:property value='riskId'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">二级风险名称：</td>
					    <td width="65%">
					       <input disabled="disabled" name="riskName" type="text" class="text" style="width:400px" value="<s:property value='riskName'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">一级风险名称：</td>
					    <td width="65%">
					       <input disabled="disabled" name="riskTypeId" type="text" class="text" style="width:400px" value="<s:property value='riskTypeId'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
						 <td width="35%" nowrap align="right">归口部门：</td>
					    <td width="65%">
					       <input disabled="disabled" name="riskDep" type="text" class="text" style="width:154px" value="<s:property value='riskDep'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">风险建立时间：</td>
					    <td>
							<input disabled="disabled" name="riskRemark" value="<s:property value='riskRemark'/>"/>
						</td>
					  </tr>
					  </table>
				</fieldset>				</td>
		</tr>
		</table>	 </td>
  </tr>
		<tr bgcolor="#F8FCFC">
			<td colspan="2" align="center" height="50px">
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Risk/Risk.jsp/dataUnit/RiskAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</td>
		</tr>
</table>
</div>
</form>
</body>
</html>