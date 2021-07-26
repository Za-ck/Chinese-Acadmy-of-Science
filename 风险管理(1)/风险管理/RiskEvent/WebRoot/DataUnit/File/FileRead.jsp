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
    
    <title>管理规定文件查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
</head>
  
 <body class="ContentBody">
<form action="dataUnit/FilReadAction" method="post" enctype="multipart/form-data" name="filReadForm" id="filReadForm" >
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>管理规定文件查看</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>管理规定文件信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
					  <tr>
					    <td nowrap align="right" width="37%">文件编号:</td>
					    <td width="15%"><span class="red">
					       <input disabled="disabled" name="fileId" type="text" class="text" style="width:154px" value="<s:property value='fileId'/>"/>*</span>						
						</td>
					    <td align="right" width="10%">文件名称:</td>
					    <td width="38%"><span class="red">
				        	<input disabled="disabled" name="fileName" type="text" class="text" style="width:200px" value="<s:property value='fileName'/>"/>*</span>
						</td>
					  </tr>
					  <tr>
					  	<td nowrap align="right" width="37%">文件类型：</td>
					  	<td>
					  		<select name="fileTypeString" id="fileTypeString" style="width: 154px" disabled="disabled">
								<option value="1" <s:if test="fileType==1">selected</s:if>>三标体系</option>
								<option value="2" <s:if test="fileType==2">selected</s:if>>管理标准</option>
								<option value="3" <s:if test="fileType==3">selected</s:if>>工作标准</option>
								<option value="4" <s:if test="fileType==4">selected</s:if>>应急预案</option>
								<option value="5" <s:if test="fileType==5">selected</s:if>>内控流程</option>
							</select>
					  	</td>
					  </tr>
					  <tr>
					  	<td align="center" colspan="4">管理文件涉及的风险：</td> 
					  </table>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" id="doc">
							<s:iterator value="risList">
								<tr>
						            <td align="center" colspan="4"><input disabled="disabled" type="text" name="riskName" align="right" style="width:150px;border:0px;text-align:center;" value="<s:property value='riskName'/>"/></td>									
 								</tr>
							</s:iterator>
					  </table>
				</fieldset>	
				</td>
		</tr>
		</table>
	 </td>
  </tr>
		<tr>
			<td colspan="2" align="center" height="50px">
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/File/File.jsp/dataUnit/FileAction'">
			</td>
		</tr>
</table>
</div>
</form>
</body>
</html>