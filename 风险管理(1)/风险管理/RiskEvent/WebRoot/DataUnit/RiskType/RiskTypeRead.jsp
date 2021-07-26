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
    
    <title>风险类型信息查看</title>
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
<form action="dataUnit/RTReadAction" method="post" enctype="multipart/form-data" name="rtReadForm" id="rtReadForm" >
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>风险类型信息查看</span></div></div>

<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  
  <tr bgcolor="#F8FCFC">
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险类型信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%" >					 
					  <tr>
					    <td nowrap align="right" width="13%">风险类型编号:</td>
					    <td width="33%">
					       <input disabled="disabled" name="rtId" type="text" class="text" style="width:154px" value="<s:property value='rtId'/>"/>	
						</td>
					    <td align="right" width="19%">风险类型名称:</td>
					    <td width="35%">
				        	<input disabled="disabled" name="rtName" type="text" class="text" style="width:154px" value="<s:property value='rtName'/>"/>
						</td>
					  </tr>				    
					  <tr>
					    <td width="13%" nowrap align="right">描述:</td>
					    <td colspan="3">
							<textarea disabled="disabled" name="rtRemark" cols="100" rows="10"><s:property value="rtRemark"/></textarea>						</td>
					  </tr>
					  </table>
				</fieldset>				</td>
		</tr>
		</table>	 </td>
  </tr>
		<tr bgcolor="#F8FCFC">
			<td colspan="2" align="center" height="50px">
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/RiskType/RiskType.jsp/dataUnit/RiskTypeAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</td>
		</tr>
</table>
</div>
</form>
</body>
</html>