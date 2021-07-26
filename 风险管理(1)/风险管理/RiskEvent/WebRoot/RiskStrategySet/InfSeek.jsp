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
    
    <title>策略信息查看</title>
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
  
 <body class="ContentBody" bgcolor="#F8FCFC">
<form action="dataUnit/LawReadAction" method="post" enctype="multipart/form-data" name="lawReadForm" id="lawReadForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>策略信息查看</span></div></div>

<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  <tr bgcolor="#F8FCFC">
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>策略信息查看</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%" >					 
					  <tr>
					    <td nowrap align="right" width="35%">策略编号:</td>
					    <td width="65%">
					       <input disabled="disabled" name="riskStrId" type="text" class="text" style="width:154px" value="<s:property value='riskStrId'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">风险策略设置最小值:</td>
					    <td width="65%">
					       <input disabled="disabled" name="stragValue" type="text" class="text" style="width:154px" value="<s:property value='stragValue'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">风险策略预警状态:</td>
					    <td>
							<textarea disabled="disabled" name="stragStatue" cols="70" rows="12"><s:property value='stragStatue'/></textarea>
						</td>
						<td width="35%" nowrap align="right">风险策略预警颜色:</td>
					    <td>
							
							<input disabled="disabled" name="stragColor" type="text" class="text" style="width:154px" value="<s:property value='stragColor'/>"/>
						</td>
						<td width="35%" nowrap align="right">风险策略设置最大值:</td>
					    <td>
							
							<input disabled="disabled" name="remark" type="text" class="text" style="width:154px" value="<s:property value='remark'/>"/>
						</td>
					  </tr>
					  </table>
				</fieldset>	
				</td>
		</tr>
		</table>
	 </td>
  </tr>
		<tr bgcolor="#F8FCFC">
			<td colspan="2" align="center" height="50px">
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/RiskStrategySet/StrategyInf.jsp/RiskStrategySet/riskStrategyInfAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
</table>
</div>
</form>
</body>
</html>