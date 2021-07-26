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
    
    <title>个人信息查看</title>
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
</head>
  
 <body class="ContentBody">
<form action="personManage/personInfoSaveAction" method="post"  name="personInfoForm" id="personInfoForm">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>个人信息查看修改</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0">	
		<tr>
			<td width="100%">			
				<fieldset>
				<legend>个人信息修改</legend>
					
					   <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" >					 
									 
					 <tr >
					    <td width="35%" nowrap align="right">员工编号：</td>
					   <td width="65%">
					       <input name="userId" type="text" class="text" style="width:154px" value="<s:property value='user.userId'/>" onfocus="this.blur()"/>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">姓名：</td>
					   <td width="65%">
					       <input name="userName" type="text" class="text" style="width:154px" value="<s:property value='user.userName'/>" onfocus="this.blur()"/>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">性别：</td>
					   <td width="65%">
					       <input name="userSex" type="text" class="text" style="width:154px" value="<s:property value='user.userSex'/>" onfocus="this.blur()"/>
						</td>
					  </tr>
					   <tr>
					    <td width="35%" nowrap align="right">所属部门：</td>
					    <td width="65%">
					       <input name="depName" type="text" class="text" style="width:154px" value="<s:property value='depName'/>"  onfocus="this.blur()"/>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">用户角色：</td>
					   <td width="65%">
					   <s:select name="user.userPosition"  theme="simple" list="#{'1':'各部门员工','2':'各部门领导','9':'系统管理员','3':'职能部门员工','4':'职能部门领导','5':'企业发展部员工','6':'企业发展部领导','7':'审计监察部领导','8':'院长工作部领导','10':'院长'}" style="width:154px" disabled="true"></s:select>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">身份证号：</td>
					   <td width="65%">
					       <input name="userIdcard" type="text" class="text" style="width:154px" value="<s:property value='user.userIdcard'/>"/>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">联系电话：</td>
					    <td width="65%">
					       <input name="userTel" type="text" class="text" style="width:154px" value="<s:property value='user.userTel'/>"/>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">E-mail：</td>
					    <td width="65%">
					       <input name="userEmail" type="text" class="text" style="width:154px" value="<s:property value='user.userEmail'/>"/>
						</td>
					  </tr>
					  </table>
				</fieldset>	
				</td>
		</tr>
		</table>
	 </td>
  </tr>
		<tr >
			<td align="center" height="50px">
				<input type="submit" name="submit" value="" class="save" onclick="javascript:history.go(-1)"/>
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
	if((operation=="update")&&(result=="success")) alert("修改个人信息成功!");
	else if(operation=="update"&&result=="fail") alert("修改个人信息失败!");
}
</script>
</html>