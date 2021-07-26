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
    
    <title>个人密码修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<script language=javascript src="/RiskEvent/js/windows.js"></script>
</head>
  
 <body>
<form action="personManage/passwordSaveAction" method="post"  name="passwordForm" id="passwordForm" onsubmit="return confirmOld();">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>个人密码修改</span></div></div>

<div class="MainDiv">
<table width="99%" border="0"  cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">	
		<table border="0" cellpadding="0" cellspacing="0">
	
		<tr>
			<td >			
				<fieldset >
				<legend>个人密码修改</legend>						
					     <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
						 
					 <tr>
					    <td width="35%" nowrap align="right">登录原密码：</td>
					   <td width="65%">
					       <span class="red"><input id="userPassword" name="userPassword" type="password" class="text" style="width:154px" value=""/>*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">登录新密码：</td>
					   <td width="65%">
					       <span class="red"> <input name="newPassword" type="password" class="text" style="width:154px"/>*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">确认新密码：</td>
					   <td width="65%">
					      <span class="red">  <input name="confirmPassword" type="password" class="text" style="width:154px"/>*</span>
						</td>
					  </tr>
					  <tr><td><input type="hidden" id="passwd" name="passwd" value="<s:property value="userPassword"/>"/></td></tr>
					  </table>
				</fieldset>	
				</td>
		</tr>
		<tr >
			<td align="center" height="50px">
				<input type="submit" name="submit" value="" class="save" />
				&nbsp;
				<input type="button" name="back" value="" class="back" onclick="javascript:history.go(-1)"/>　
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		</table>
	
		
</table>
</div>
</form>
</body>
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
	if((operation=="update")&&(result=="success")) alert("修改密码成功!");
	else if(operation=="update"&&result=="fail") alert("修改密码失败!");
}
function confirmOld(){
if(document.getElementById('userPassword').value!=document.getElementById('passwd').value)
{alert('输入原密码不正确！');
return false;}
else
{
if(doValidate('passwordForm'))
{
return true;
}
else 
{
return false;
}}
}
</script>
</html>