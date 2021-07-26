<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>确认提交</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
 <body class="ContentBody">
<form id="SubmitConfirm" name="SubmitConfirm" action="riskInput/SubmitConfirmAction" method="post" enctype="multipart/form-data" >
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>风险事件提交</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" align="center">	
		<tr>
			<td width="100%">			
				<fieldset>
				<legend>确认提交</legend>
					 <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:80%" align="center">		                  				    
					  <tr>
					    <td width="15%" height="148" rowspan="4" align="center" nowrap>&nbsp;</td>
					    <td height="35" colspan="2" >
					      <p>信息已录入完，是否提交？ </p>
					     					    </td>
					  </tr>
					  <tr>
					    <td height="20" colspan="2">&nbsp;</td>
				       </tr>
					  <tr>
					    <td width="16%" height="46" align="right">提交到部门：</td>
				        <td width="71%"><input name="submittodep" type="text" class="text" readonly="readonly" style="width:154px" value="<s:property value='submittodep'/>"/>
		              	</td>
					  </tr>
					  <tr>
					    <td height="46" align="right">提交到人：</td>
				        <td width="33%"><span class="red">
                          <s:select name="submittopeople" style="width:100px" label="提交到人" labelposition="left" theme="simple" list="usersList" listKey="userId" listValue="userName" ></s:select>                                            
                        *</span></td>
					  </tr>
			    </table>
				</fieldset>	
				</td>
		</tr>
		</table>	 
	</td>
  </tr>
		
		<tr>
			<td colspan="2" align="center" height="50px">
			    <input type="button" name="button1" value="返回上一页" onClick="window.location.href='riskInput/getRiskManageAction?idmanage=<s:property value='idmanage'/>'"/>
				<input type="submit" name="submit" value="提交" />　  
		   		<input type="button" name="button" value="取消" onClick="window.location.href='riskInput/RiskEventInputQueryAction'"/>
		   		
			</td>
		</tr>
		<tr>
  		 <td colspan="2"><input type="hidden" name="idmanage" id="idmanage" value="<s:property value="idmanage"/>"/></td>
  		</tr>

</table>
</div>
</form>
<script>
 function returnmanage()
 {
  SubmitConfirm.action="riskInput/getRiskManageAction";
  SubmitConfirm.submit();
 };
</script>
</body>
</html>
