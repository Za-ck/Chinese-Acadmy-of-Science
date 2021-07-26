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
    
    <title>系统用户信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<script language=javascript src="/RiskEvent/js/windows.js"></script>
</head>
  
 <body class="ContentBody" onload="init()">
<form name="userAddUpdate" id="userAddUpdate" action="systemManage/UMAddUpdateAction" method="post" onSubmit="return doValidate('userAddUpdate')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>系统用户信息</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" align="center">	
		<tr>
			<td width="100%">			
				<fieldset>
				<legend>新增/修改用户信息</legend>
					
					   <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
									 
					 <tr>
					    <td width="35%" nowrap align="right">登录账号：</td>
					   <td width="65%">
					       <input name="userId" type="text" class="text" style="width:154px" value="<s:property value='userId'/>" />
						 <span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">姓名：</td>
					   <td width="65%">
					       <input name="userName" type="text" class="text" style="width:154px" value="<s:property value='userName'/>" />
						 <span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">性别：</td>
					   <td width="65%">
					   	<s:select name="userSex"  theme="simple" list="#{'男':'男','女':'女'}" style="width:154px"  ></s:select>
						<span class="red">*</span>
						</td>
					  </tr>
					   <tr>
					    <td width="35%" nowrap align="right">所属部门：</td>
					    <td width="65%">
					       <s:select name="depId" theme="simple" list="alldepList" listValue="depName" listKey="depId" class="text" style="width:240px" onchange="changeDep()"></s:select>
						<span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">用户角色：</td>
					   <td width="65%">
						<s:select name="userPosition"  theme="simple" list="allroleList" listValue="srName" listKey="srId" class="text"  style="width:154px" ></s:select>		
						<span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">办公电话：</td>
					    <td width="65%">
					       <input name="userTel" type="text" class="text" style="width:154px" value="<s:property value='userTel'/>"/>
						<span class="red">*</span>
						</td>
					  </tr>
					   <tr>
					    <td width="35%" nowrap align="right">手机：</td>
					    <td width="65%">
					       <input name="userCellphone" type="text" class="text" style="width:154px" value="<s:property value='userCellphone'/>"/>
						<span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					    <td width="35%" nowrap align="right">E-mail：</td>
					    <td width="65%">
					       <input name="userEmail" type="text" class="text" style="width:154px" value="<s:property value='userEmail'/>"/>
						<span class="red">*</span>
						</td>
					  </tr>
					   <tr>
					    <td width="35%" nowrap align="right">身份证号：</td>
					   <td width="65%">
					       <input name="userIdcard" type="text" class="text" style="width:154px" value="<s:property value='userIdcard'/>"/>
						</td>
					  </tr>
					  <tr style="display:none" id="publishInfo">
					    <td width="35%" nowrap align="right">发布人工号：</td>
					   <td width="65%">
					       <input name="publishId" type="text" class="text" style="width:154px" value="<s:property value='publishId'/>"/>
					       <span class="red">*</span>
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
				<input type="submit" name="submit" value="" class="save"/>
				&nbsp;	
				<a type="button" name="back" value="" class="back" onClick="back()"/>　
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
			</td>
		</tr>
		
		<tr bgcolor="#F8FCFC"><td align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		<tr bgcolor="#F8FCFC"><td align="left"><input type="text" style="visibility:hidden" name="userPassword" id="userPassword" value="<s:property value='userPassword'/>"/></td></tr>
		<tr bgcolor="#F8FCFC"><td align="left"><input type="text" style="visibility:hidden" name="current_pagenum" id="current_pagenum" value="<s:property value='current_pagenum'/>"/></td></tr>

</table>
</div>
</form>
</body>
<SCRIPT type="text/javascript">
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
	else if(operation=="addupdate"&&result=="fail") alert("提交失败，该登录账号已存在!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
function init()
{
	var depselect = document.getElementById("depId");
	var index = depselect.selectedIndex; // 选中索引
	var text = depselect.options[index].text; // 选中文本
	var value = depselect.options[index].value; // 选中值
	if(value == "FB") 
	{
		var publish = document.getElementById("publishInfo");
		publish.style.display = "";
	}
}

function changeDep()
{
	
	var depselect = document.getElementById("depId");
	var index = depselect.selectedIndex; // 选中索引
	var text = depselect.options[index].text; // 选中文本
	var value = depselect.options[index].value; // 选中值
	if(value == "FB") 
	{
		var publish = document.getElementById("publishInfo");
		publish.style.display = "";
		var position = document.getElementById("userPosition");
		for (var i = 0; i < position.options.length; i++) 
		{
			if (position.options[i].value == "12") 
			{
				position.options[i].selected = true;
				break;
			} 
		}
	}
	else {
		var publish = document.getElementById("publishInfo");
		publish.style.display = "none";
	}
}
function back()
{//2019.4
    //alert("0000000000");
    //userAddUpdate.action="systemManage/UUpdateBackAction";
   // userAddUpdate.sumbit();
    var depId=document.getElementById("depId").value;
    var updateFlag=document.getElementById("updateFlag").value;
    window.location.href='systemManage/UUpdateBackAction?depId='+depId+'&updateFlag='+updateFlag;
}

</SCRIPT>
</html>