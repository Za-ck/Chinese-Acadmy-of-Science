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
    
    <title>影响评定等级查看</title>
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
<form action="dataUnit/SafReadAction" method="post" enctype="multipart/form-data" name="safReadForm" id="safReadForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>针对安全健康环境的影响评定等级</span></div></div>

<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  
  <tr bgcolor="#F8FCFC">
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>查看影响评定等级信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
					  <tr>
					    <td nowrap align="right" width="35%">等级编号:</td>
					    <td width="65%">
					       <input disabled="disabled" name="safId" type="text" class="text" style="width:154px" value="<s:property value='safId'/>"/>
						</td>
					  </tr>	
					  <tr><td height="10"></td></tr>				 
					  <tr>
					    <td nowrap align="right" width="35%">影响程度:</td>
					    <td width="65%">
					       <input disabled="disabled" name="safLevel" type="text" class="text" style="width:154px" value="<s:property value='safLevel'/>"/>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">安全事故:</td>
					    <td>
							<textarea disabled="disabled" name="safSafety" cols="70" rows="8"><s:property value='safSafety'/></textarea>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">环境描述:</td>
					    <td>
							<textarea disabled="disabled" name="safEnvironment" cols="70" rows="8"><s:property value='safEnvironment'/></textarea>
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
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Safe/Safe.jsp/dataUnit/SafeAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
</table>
</div>
</form>
</body>
</html>