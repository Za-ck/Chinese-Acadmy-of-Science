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
    
    <title>请先查询后再导出</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="" name="">
	<table id="" width="100%" class="tablestyle">
		<thead align="center">					
		  <tr>
			<td><a  onclick="window.history.go(-1)" ><img src="images/backdetail.png" style="width:32px;height:32px"/><span>返回</span></a>
     	</tr>	
     	<tr>			
			  <td>请先点击"统计查询"按钮后再导出</td>
		  </tr>	
	</table>
	</form>
</body>
</html>
