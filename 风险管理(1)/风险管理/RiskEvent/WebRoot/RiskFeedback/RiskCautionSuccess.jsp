<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="p"  uri="pageV"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DepCautionSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script src="/RiskEvent/js/jquery-1.7.1.min.js"></script>
    <script src="/RiskEvent/js/colorpick/jquery.colorpicker.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
 .blg{
    cursor:pointer;
	margin-top:10px;
	margin-left:10px;
	width:90px;
	height:45px;
	color:#ffffff;
	background-color:#0066FF;
	text-align:center;
	float:left;}
</style>
  </head>
  
  <body>
  <br>
  <font class="blg" color="#0000FF">警告成功</font>  
    <br/>
    <br/>
    <br>
    <br>
    <br>
    <br>
      <tr >
      
			<td colspan="2" align="center" height="50px">
				
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='riskFeedback/RiskCautionAction'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		</body>
</html>
