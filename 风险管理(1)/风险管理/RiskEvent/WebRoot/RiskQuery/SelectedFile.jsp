<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>根据事件编号点击后出现涉及的流程文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
</head>

<body style="margin:0px;text-align: center">
<form id="SelectedFile" name="SelectedFile">
	<table id=""  border="1" cellspacing="0" cellpadding="0" class="linktable">
		<thead align="center">
			<tr>
			  <th align="center" width="50" height="20">序号</th>
			  <th align="center" width="150">文件编号</th>
			  <th align="center" width="300">文件名称</th>
			  <%int num=0;%>
			</tr>
		</thead>
		<tbody align="center">		
		<s:iterator value="fileList">	
			<tr>
				<td align="center"><%=++num%></td>
			    <td align="center"><s:property value="fileId"/></td>
			    <td align="center"><s:property value="fileName"/></td>
		  </tr>
		  </s:iterator>
		  <s:if test="fileList.size()==0">
		  <tr>
			  <td>暂无数据</td>
			  <td>暂无数据</td>
			  <td>暂无数据</td>
		  </tr>
		  </s:if>
		</tbody>	
	</table>
	</form>
</body>
</html>
