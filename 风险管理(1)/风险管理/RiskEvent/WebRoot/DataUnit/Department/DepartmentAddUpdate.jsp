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
    
    <title>部门信息添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
<body>
<form action="dataUnit/DepAddUpdateAction" method="post" enctype="multipart/form-data" name="depAddUpdateForm" id="depAddUpdateForm" onSubmit="return doValidate('depAddUpdateForm')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>部门信息新增修改</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>部门信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" >
					  <tr>
					    <td width="35%" nowrap align="right">部门编号:</td>
					    <td width="65%"><span class="red">
						   <input name="depId" type="text" class="text" style="width:154px" value="<s:property value='depId'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td nowrap align="right" width="35%">部门名称:</td>
					    <td width="65%"><span class="red">
					       <input name="depName" type="text" class="text" style="width:154px" value="<s:property value='depName'/>"/>*</span>
						</td>
					  </tr>
					 
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">是否归口部门:</td>
					    <td width="65%">		
						    <input type="radio"  name="depCheck" value="1" onChange="select1()"  >是&nbsp;&nbsp;&nbsp;&nbsp;
						    <input type="radio"  name="depCheck" value="0" onChange="select2()">否&nbsp;&nbsp;<span class="red">*</span>
						</td>
					  </tr>
					  
					  <!-- <li style="float: left; margin-left: 10px; font-size: 14px;">
							查询时间：
							<s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}"  style="width:154px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>

							<input name="raYear" class="Wdate" style="width: 105px;"
							     
								value="<s:property value='raYear'/>"
								onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
						</li>
						
						<span class="red">
						   <input disabled="disabled" name="depId" type="text" class="text" style="width:154px" value="<s:property value='depId'/>"/>*</span>
						
						 -->
					 <!--    <tr>
					    <td nowrap align="right" width="35%">归属部门名称:</td>
					    <td width="65%"><span class="red">
					       <input name="depbelName" type="text" class="text" style="width:154px" value="<s:property value='depbelName'/>"/>*</span>
						</td>
					  </tr> -->
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">备注:</td>
					    <td>
							<textarea name="depRemark" cols="50" rows="6"><s:property value='depRemark'/></textarea>
						</td>
					  </tr>
					    <tr>					
					    <td>
						   <input name="depQueue" type="hidden"  value="<s:property value='depQueue'/>"/>
						   <input name="depAssess" type="hidden" value="<s:property value='depAssess'/>"/>
						</td>
					  </tr>
					  
					  </table>
				</fieldset>				</td>
		</tr>
		</table>	 </td>
  </tr>
		<tr  >
			<td colspan="2" align="center" height="50px">
				<input type="Submit" name="submit1" value="" class="save"/>　
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Department/Department.jsp/dataUnit/DepartmentAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</td>
		</tr>
		<tr  bgcolor="#F8FCFC" ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		<tr  bgcolor="#F8FCFC" ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="depSort" id="depSort" value="<s:property value='depSort'/>"/></td></tr>
		<tr bgcolor="#F8FCFC"><td align="left"><input type="text" style="visibility:hidden" name="current_pagenum" id="current_pagenum" value="<s:property value='current_pagenum'/>"/></td></tr>
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

//var flag=document.getElementById("updateFlag").value;
var depChecks=document.all.depCheck;
var depSorts=document.getElementById("depSort");
/*if(flag==""){
	depChecks[1].checked="checked";
}
else{
if(depSorts==0) depChecks[1].checked="checked";
else depChecks[0].checked="checked";
}*/
if(depSorts.value=="1"){
	depChecks[0].checked="checked";
}
else{
	depChecks[1].checked="checked";
	depSorts.value="0";//当没有对两个单选按钮做任何动作时，depSorts.value是空的，故给其赋值为0
}
/*var num=document.getElementById("depSort").value;

function save(){
	document.getElementById("depSort").value=request.getParameter("depSortCheck");
	depAddUpdateForm.action="dataUnit/DepAddUpdateAction";
	depAddUpdateForm.submit();
}  */
function select1(){
	depSorts.value="1";
}
function select2(){
	depSorts.value="0";
} 
</script>
</body>
</html>