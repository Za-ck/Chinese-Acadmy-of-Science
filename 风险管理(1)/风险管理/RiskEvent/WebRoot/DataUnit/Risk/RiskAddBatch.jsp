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
    
    <title>批量新增风险</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
  	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
	<script language="javascript" src="IconPage/page.js"></script>	
	
</head>
  
<body>
<form action="dataUnit/RisAddBatchAction" method="post" enctype="multipart/form-data" name="risAddBatchForm" id="risAddBatchForm"   onSubmit="return checksub()">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>批量新增风险</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">	
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" >
					  
					<tr>
					 	<td  width="35%" nowrap align="right">风险被复制的年份：</td>
     					<td width="60"> 
     					 <input name="yearBefore" id="yearBefore" style="width:90px;" class="Wdate" value="<s:property value='yearBefore'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     					</td>
     				</tr>
					<tr>
					 	<td  width="35%" nowrap align="right">风险复制后的年份：</td>
     					<td width="60"> 
     					 <input name="yearAfter" id="yearAfter" style="width:90px;" class="Wdate" value="<s:property value='yearAfter'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     					</td>
     				</tr>
					  
					   
					  </table>
				</fieldset>				</td>
		</tr>
		</table>	 </td>
  </tr>
		<tr  >
			<td colspan="2" align="center" height="50px">
				<input type="submit" name="saverisk" value="" class="save" />　
				<input type="button" name="backrisk" value="" class="back" onClick="window.location.href='/RiskEvent/DataUnit/Risk/Risk.jsp/dataUnit/RiskAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</td>
		</tr>
		

</table>
</div>
</form>

<script type="text/javascript">
function checksub()
{
	var yearBefore=document.getElementById('yearBefore').value;
	var yearAfter=document.getElementById('yearAfter').value;
	var yearBeforeInt=parseInt(yearBefore);
	var yearAfterInt=parseInt(yearAfter);
 	if(yearBeforeInt < 2012)
	{
		alert("被复制年份 请选择2012年以后");
		return false;
	}
	else if(yearAfterInt < 2012)
	{
		alert("复制后年份 请选择2012年以后");
		return false;
	}
	else if(yearBeforeInt >= yearAfterInt)
	{
		alert("被复制年份 应小于 复制后年份");
		return false;
	}
	else
	{
		openBox('批量新增提示',350,150,0,'正在批量新增中，请等待....','');
		return true;
	}
}

</script>
</body>
</html>