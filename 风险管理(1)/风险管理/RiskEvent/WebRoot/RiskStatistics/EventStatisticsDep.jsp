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
    
    <title>根据事件编号统计后点击录入部门个数显示具体部门</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
</head>

<body style="margin:0px;text-align: center">
<form id="StaQueryDep" name="StaQueryDep">
	<table id=""  border="1" cellspacing="0" cellpadding="0" class="linktable">
		<thead align="center">
			<tr>
			  <th width="50%">部门编号</th>
			  <th width="50%">部门名称</th>
			</tr>
		</thead>
		<tbody align="center">		
		<s:iterator value="depList">	
			<tr>
			  <td><s:property value="depId"/></td>
			  <td><s:property value="depName"/></td>
		  </tr>
		  </s:iterator>
		  <s:if test="depList.size()==0">
		  <tr>
			  <td>暂无数据</td>
			  <td>暂无数据</td>
		  </tr>
		  </s:if>
		</tbody>	
	</table>
	</form>
</body>
</html>
