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
    
    <title>中南电力设计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/js/windows.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
  <style type="text/css"></style>
<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />
</head>
  
 <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <form name="login" action="default/loginAction" method="post">
<table width="100%" border="0" height="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td height="599" background="images/login_01.gif">&nbsp;</td>
    <td width="1024">
    <!-- ImageReady Slices (1_120531001852_1.psd) -->
<table id="__01" width="1024" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td rowspan="11">
			<img src="images/1_120531001852_1_01.gif" width="272" height="580" alt=""></td>
		<td colspan="6">
			<img src="images/login_02.gif" width="495" height="60" alt=""></td>
		<td rowspan="11">
			<img src="images/login_03.gif" width="257" height="580" alt=""></td>
	</tr>
	<tr>
		<td colspan="2">
			<img src="images/login_04.gif" width="196" height="58" alt=""></td>
		<td colspan="4">
			<img src="images/login_05.gif" width="299" height="58" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_06.gif" width="495" height="46" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_07.gif" width="495" height="58" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_08.gif" width="192" height="29" alt=""></td>
		<td colspan="4" width="151" height="29">
			<input name="userid" type="text"  style="width:150px;height:26px; border:0px; border-style:none;line-height:26px; background-image:url(images/login_09.gif)"/></td>
		<td>
			<img src="images/login_10.gif" width="152" height="29" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_11.gif" width="495" height="22" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_12.gif" width="192" height="29" alt=""></td>
		<td colspan="4"  background="images/login_13.gif" width="151" height="29">
			<input name="password" type="password" style="width:150px;height:26px; vertical-align:middle; line-height:26px;border:0px; border-style:none; background-image:url(images/login_13.gif)"/></td>
		<td>
			<img src="images/login_14.gif" width="152" height="29" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_15.gif" width="495" height="18" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="images/1_120531001852_1_17.gif" width="280" height="24" alt=""></td>
		<td>
            <input name="Submit2" type="submit" value="" style="width:54px; border:0px; cursor:pointer; height:24px;background-image:url(images/login_17.gif)"/>
            </td>
		<td colspan="2">
			<img src="images/1_120531001852_1_18.gif" width="161" height="24" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_19.gif" width="495" height="107" alt=""></td>
	</tr>
	<tr>
		<td colspan="6">
			<img src="images/login_20.gif" width="495" height="129" alt=""></td>
	</tr>
	<tr>
		<td colspan="8" style="background-color:#f5f5f5; height:19px;">&nbsp;
			</td>
	</tr>
	<tr style="background-color:#f5f5f5;">
		<td style="background-color:#f5f5f5;" width="272" height="1">
		</td>
		<td style="background-color:#f5f5f5;" width="192" height="1">
			</td>
		<td style="background-color:#f5f5f5;" width="4" height="1">
			</td>
		<td style="background-color:#f5f5f5;" width="84" height="1" >
			</td>
		<td style="background-color:#f5f5f5;" width="54" height="1">
			</td>
		<td style="background-color:#f5f5f5;" width="9" height="1">
			</td>
		<td style="background-color:#f5f5f5;" width="152" height="1">
			</td>
		<td style="background-color:#f5f5f5;" width="257" height="1">
			</td>
	</tr>
</table>
<!-- End ImageReady Slices -->
    </td>
    <td background="images/login_01.gif">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" style="background-color:#f5f5f5;">&nbsp;</td>
  </tr>
</table>


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
	if(operation=="login"&&result=="fail") alert("登录失败，用户名或密码错误!");
}
</SCRIPT>
</html>
