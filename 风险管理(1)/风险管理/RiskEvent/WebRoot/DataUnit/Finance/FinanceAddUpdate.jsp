<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>  
    <base href="<%=basePath%>"/>
    
    <title>影响评定等级添加</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<link href="/RiskEvent/js/Scroll/scroll.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/Scroll/scroll.js"></script>
</head>
  
 <body>
<form action="dataUnit/FinAddUpdateAction" method="post" enctype="multipart/form-data" name="finAddUpdateForm" id="finAddUpdateForm" onsubmit="return doValidate('finAddUpdateForm')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>针对财务的影响评定等级新增修改</span></div></div>
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
					  <table border="0"  class="contentInfo" cellpadding="2" cellspacing="1" style="width:100%" >
					  <tr>
					    <td  align="right" width="35%">等级编号:</td>
					    <td width="65%"><span class="red">
					       <input name="finIdString" type="text" class="text" style="width:154px" value="<s:property value='finId'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>					 
					  <tr>
					    <td  align="right" width="35%">影响程度:</td>
					    <td width="65%"><span class="red">
					       <input name="finDetail" type="text" class="text" style="width:154px" value="<s:property value='finDetail'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%"  align="right">净资产:</td>
					    <td>
							<textarea name="finAsset" cols="50" rows="5"><s:property value='finAsset'/></textarea>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
						 <td width="35%"  align="right">营业收入:</td>
					    <td>
							<textarea name="finIncome" cols="50" rows="5"><s:property value='finIncome'/></textarea>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%"  align="right">利润总额:</td>
					    <td>
							<textarea name="finProfit" cols="50" rows="5"><s:property value='finProfit'/></textarea>
						</td>
					  </tr>
					  <tr><td height="2"></td></tr>
					  <tr>
					    <td width="35%"  align="right">资产总额:</td>
					    <td>
							<textarea name="finProperty" cols="50" rows="5"><s:property value='finProperty'/></textarea>
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
				<input type="button" name="Submit2" value="" class="back" onclick="window.location.href='/RiskEvent/DataUnit/Finance/Finance.jsp/dataUnit/FinanceAction'"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		
</table>
</div>
<div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
<script type=text/javascript>goTopEx();</script>
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