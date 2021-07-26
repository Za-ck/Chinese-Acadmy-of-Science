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
    
    <title>影响评定等级添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
 <body >
<form action="dataUnit/RepAddUpdateAction" method="post" enctype="multipart/form-data" name="repAddUpdateForm" id="repAddUpdateForm" onSubmit="return doValidate('repAddUpdateForm')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>针对声誉的影响评定等级新增修改</span></div></div>

<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
  <tr>
    <td class="CPanel">		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >	
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>影响评定等级信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table border="0" class="contentInfo"  cellpadding="2" cellspacing="1" style="width:100%" >
					  <tr>
					    <td nowrap align="right" width="35%">等级编号:</td>
					    <td width="65%"><span class="red">
					       <input name="repIdString" type="text" class="text" style="width:154px" value="<s:property value='repId'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">影响程度:</td>
					    <td width="65%"><span class="red">
					       <input name="repLevel" type="text" class="text" style="width:154px" value="<s:property value='repLevel'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">企业声誉:</td>
					    <td><span class="red">
							<textarea name="repDetail" cols="50" rows="5"><s:property value='repDetail'/></textarea>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
						 <td width="35%" nowrap align="right">监管机构或上级单位:</td>
					    <td><span class="red">
							<textarea name="repSuperior" cols="50" rows="5"><s:property value='repSuperior'/></textarea>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">合作伙伴:</td>
					    <td><span class="red">
							<textarea name="repPartner" cols="50" rows="5"><s:property value='repPartner'/></textarea>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%" nowrap align="right">员工或公众关注:</td>
					    <td><span class="red">
							<textarea name="repPublic" cols="50" rows="5"><s:property value='repPublic'/></textarea>*</span>
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
			<td colspan="2" align="center" height="40px">
				<input type="submit" name="submit" value="" class="save" />　
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Reputation/Reputation.jsp/dataUnit/ReputationAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr  ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		
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
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
</script>
</html>