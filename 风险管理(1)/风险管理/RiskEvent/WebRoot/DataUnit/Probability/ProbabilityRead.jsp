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
    
    <title>发生可能性评定等级查看</title>
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
<form action="dataUnit/ProReadAction" method="post" enctype="multipart/form-data" name="proReadForm" id="proReadForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>发生可能性评定等级</span></div></div>

<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr bgcolor="#F8FCFC">
    <td class="CPanel">		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				 <legend>查看评定等级信息</legend>
				 
				 	
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%" >					 
					  <tr>
					    <td nowrap align="right" width="35%">等级编号:</td>
					    <td width="65%">
					       <input disabled="disabled" name="proId" type="text" class="text" style="width:154px" value="<s:property value='proId'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">可能性:</td>
					    <td width="65%">
					       <input disabled="disabled" name="proLevel" type="text" class="text" style="width:154px" value="<s:property value='proLevel'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					   <tr>
							<td align="right" width="35%">发生概率:</td>
					   		<td width="65%">
				        		<input disabled="disabled" name="proProbability" type="text" class="text" style="width:154px" value="<s:property value='proProbability'/>"/>
							</td>
						</tr>
						<tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">针对大型灾害或事件类:</td>
					    <td>
							<textarea disabled="disabled" name="proDisasterEvent" cols="50" rows="4"><s:property value='proDisasterEvent'/></textarea>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
						 <td width="35%" nowrap align="right">针对日常运营:</td>
					    <td>
							<textarea disabled="disabled" name="proDailyOperation" cols="50" rows="4"><s:property value='proDailyOperation'/></textarea>
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
			<td align="center" height="50px">
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Probability/Probability.jsp/dataUnit/ProbabilityAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
</table>
</form>
</body>
</html>