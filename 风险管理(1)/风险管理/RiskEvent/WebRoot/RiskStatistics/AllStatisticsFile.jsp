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
    
    <title>汇总统计后点击涉及流程显示具体的文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
</head>

<body>
<form id="StaQueryDep" name="StaQueryDep">
	<table id=""  border="1" cellspacing="0" cellpadding="0" class="linktable">
		<thead align="center">
			<tr>
			  <th width="50%">管理规定编码</th>
			  <th width="50%">管理规定名称</th>
			</tr>
		</thead>
		<tbody align="center">			
		<s:iterator value="efileList">	
			<tr>
			  <td><s:property value="fileId"/></td>
			  <td><s:property value="fileName"/></td>
		  </tr>
		  </s:iterator>		
		  <s:if test="efileList.size()==0">
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
