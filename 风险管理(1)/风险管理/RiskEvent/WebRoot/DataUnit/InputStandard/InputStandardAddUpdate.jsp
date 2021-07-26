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
    
    <title>填报标准添加与修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
 <body>
<form action="dataUnit/InputStandardAddUpdateAction" method="post" enctype="multipart/form-data" name="standardAddUpdateForm" id="standardAddUpdateForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>填报标准新增修改</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" > 
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >	
		<tr>
			<td width="100%">
				<fieldset style="height:100%;">
				<legend>填报标准信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table border="0"  class="contentInfo" cellpadding="2" cellspacing="1" style="width:100%">
					  
					   <tr>
					  
					    <td nowrap align="right" width="35%">部门属性编号:</td>
					    <td width="65%"><span class="red"><s:property value='depProporty'/></span>
						</td>
					  </tr>
					  <tr>
					  
					    <td nowrap align="right" width="35%">部门属性:</td>
					    <td width="65%"><span class="red"><s:property value='depProportyName'/></span>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">需填报数:</td>
					    <td width="65%"><span class="red">
					       <input name="rdInputStandardString" type="text" class="text" style="width:154px" value="<s:property value='inputNeed'/>"/>*</span>
						</td>
					  </tr>
					  </table>
				</fieldset>	
				</td>
			
		</tr>
		</table>
	 </td>
  </tr>
		<tr  >
			<td colspan="2" align="center" height="50px">
				<input type="submit" name="submit1" value="" class="save"/>　
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/InputStandard/InputStandard.jsp/dataUnit/InputStandardAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr  ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		<tr  ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="id" id="id" value="<s:property value='id'/>"/></td></tr>
		
</table>
</div>
</form>
<script type="text/javascript">
<%
  String params=request.getParameter("operation");
  String[] temps=null;
  String operation="",result="";
  if(params!=null){
	 temps=params.split("\\*");	 
      operation=temps[0];
      result=temps[1].split("=")[1];
 }
%>
var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
</script>
</body>
</html>