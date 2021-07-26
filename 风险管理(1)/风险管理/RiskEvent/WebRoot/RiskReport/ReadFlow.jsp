<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>${title }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" />
	<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
	
	<style type="text/css">

		html, body {
    		width:100%;
    		height:100%;
    		margin:0px;
    		padding:10px 0px;
		}

		.container {
			width: 90%;
			height: 100%;
			margin-left:auto;
			margin-right:auto;
		}

		.sidebar {
			padding: 10px 0;
			width: 80%;
			text-align: left;
		}
		.content {
			padding: 10px 0;
			width: 80%;
			text-align: left;
		}

	</style>
</head>

<body style="text-align:center; height:100%">

<div class="container">
  <div class="sidebar">
    	工作跟踪：<br/>
  		<table id='reportRecord' class='tablestyle' width='100%' >
			<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
				<td align="center">序号</td>
				<td align="center">姓名</td>
				<td align="center">部门</td>
				<td align="center">报告名称</td>
				<td align="center">所属流程</td>
				<td align="center">任务名称</td>
				<td align="center">处理时间</td>
			</tr>
			<c:forEach items="${recordlist }" var="record" varStatus="status">
				
				<%
					String styleclass = "";
				 %>
				
				<c:if test="${userId eq record.retUserId }">
					<% styleclass = "red"; %>
				</c:if>
				<tr>
					<td><span class="<%=styleclass %>">${status.index+1 }</span></td>
					<td><span class="<%=styleclass %>">${record.retUserName }</span></td>
					<td><span class="<%=styleclass %>">${record.retDepName }</span></td>
					<td><span class="<%=styleclass %>">${record.retReportName }</span></td>
					<td><span class="<%=styleclass %>">${record.frFlowName }</span></td>
					<td>
						<span class="<%=styleclass %>">${record.rfAction }</span>
						<c:if test="${fn:trim(record.retTaskFlag) eq '1' }">
							<span style="color: blue;">[撤回]</span>
						</c:if>
					</td>
					<td><span class="<%=styleclass %>">${record.retProcessTime }</span></td>
				</tr>
    		</c:forEach>						
		</table>
    
  </div>
  <div class="content">
    	流程展示：<br/>
    	<img alt="流程展示" src="${flowImage }" style="max-width: 100%;">
  </div>
  </div>
</body>
</html>
