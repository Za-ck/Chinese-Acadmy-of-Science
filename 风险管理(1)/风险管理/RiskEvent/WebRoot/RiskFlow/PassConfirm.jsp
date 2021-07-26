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
    
    <title>风险流转</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
 <body class="ContentBody">
<form action="riskFlow/RiskEventPassAction" method="post" enctype="multipart/form-data" name="FlowConfirm" id="FlowConfirm" onSubmit="">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>风险事件流转</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend></legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
					  <tr>
					    <td height="10"></td>					   
					  </tr>
					  <tr><td height="10" nowrap align="center" width="35%">是否确定通过此风险事件？</td></tr>
					  <tr><td height="10"></td></tr>
					  </table>
				</fieldset>	
				</td>
		</tr>
		</table>
	 </td>
  </tr>
		<tr>
			<td colspan="2" align="center" height="50px">
				<input type="submit" name="submit" value="通过" class="button" />　
				<input type="button" name="button1" value="返回上一页" class="button" onClick="window.location.href='riskFlow/DepVerifyAction?riskeventid=<s:property value='riskId'/>'">
			</td>
		</tr>
		<tr>
  		 <td colspan="2"><input type="hidden" name="id" id="id" value="<s:property value="riskId"/>"/></td>
  		</tr>
  		<tr>
  		 <td colspan="2"><input type="hidden" name="nextstate" id="nextstate" value="<s:property value="nextstate"/>"/></td>
  		</tr>
  		<tr>
  		 <td colspan="2"><input type="hidden" name="feedback" id="act" value="<s:property value="feedback"/>"/></td>
  		</tr>
</table>
</div>
</form>
</body>
</html>