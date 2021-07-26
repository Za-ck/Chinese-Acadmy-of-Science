<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>待办工作</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script src="/RiskEvent/IconPage/page.js" type="text/javascript"></script>
	<script src="/RiskEvent/IconPage/jquery-1.3.2.min.js" type="text/javascript"></script>

	<script type="text/javascript">
	
		function submitform()
		{
			var work = $("#schedule option:selected").val();
			
			if(work == "0")
			{
				WorkQuery.action = "default/processAction_unProcessed";
				WorkQuery.submit();
			}
			else
			{
				WorkQuery.action = "default/processAction_processed?current_pagenum=1&category=riskevent";
				WorkQuery.submit();
			}
		}
		
		function handle(url,tabname) 
		{
			window.parent.openTab(url,tabname);
		}
		
		function deleteUnProcess(url)
		{
			if(!confirm("确定删除吗?")) 
			{
				return;
			}
			var params="tmpnum="+new Date().getTime();
			$.ajax({
               url: url,
               type: 'post',
               dateType: 'json',
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
               		if(data == "success")
               		{
               			submitform();
               		}
               		
               },
               error: function () 
               {
               		alert("操作失败");
           	   }
       		});
		}
		
		function init()
		{
			var currentId = window.parent.getCurrentTabId();
			if(currentId !== "tab_id_index1") {
				window.parent.unprocessed();
			}
			
		}
		
	</script>
	
</head>

<body style="font-size:12px; margin-top:5px;" onload="init();">

	<form action="default/processAction_unProcessed" name="WorkQuery" id="WorkQuery" method="post">

		<div class="toptitle">
			<div class="toptitleleft"></div>
			<div class="topttileright"></div>
			<div class="toptitlemiddle">
				<span>待办工作</span>
			</div>
		</div>
		<div class="queryDiv">
				<div style="display: inline text-align: center">
					<ul style="list-style-type: none; width: inherit; margin-top: 5px;">
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							工作状态：
							<select name="schedule" id="schedule" style="width: 150px" onchange="submitform()">
		       					<option value="0">待办工作</option>
		       					<option value="1">已办工作</option>
							</select>
						</li>
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							类别：
							<select name="category" id="category" style="width: 150px" onchange="submitform()">
								<option value="">---请选择---</option>
								<option value="riskevent" <c:if test="${category=='riskevent'}">selected</c:if>>风险事件填报</option>
								<option value="riskreport" <c:if test="${category=='riskreport'}">selected</c:if>>风险报告</option>
								<option value="riskreply" <c:if test="${category=='riskreply'}">selected</c:if>>风险应对</option>
							</select>
						</li>
						
					</ul>
					&nbsp;&nbsp;&nbsp;<a onClick="submitform()"><img border='0' style='cursor: pointer;' title='刷新' src='/RiskEvent/IconPage/images/load.png' /></a>
				</div>
			</div>
		<div id='pagetable'>
			<table id='mytable' class='tablestyle' width='100%' height='100%'>
				<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
					<td width="10%" align="center" height="30">序号</td>
					<td width="10%" align="center">类别</td>
					<td width="15%" align="center">名称</td>
					<td width="10%" align="center">填写部门</td>
					<td width="10%" align="center">流转状态</td>
					<td width="10%" align="center">填写人</td>
					<td width="10%" align="center">最后处理时间</td>
					<td width="10%" align="center">操作</td>
					<td width="5%" align="center">删除</td>
					<%
						int num = 0;
					%>
				</tr>
				<c:forEach items="${formlist}" var="form">
					<tr title="双击查看详细信息" onDblClick="handle('${form.detailurl}','查看详情-${form.name }')">
						<td align="center"><%=++num%></td>
						<td align="center">${form.category }</td>
						<td align="center">${form.name }</td>
						<td align="center">${form.depname }</td>
						<td align="center"><a href="${form.flowurl }" target="_blank">${form.flowstate }</a></td>
						<td align="center">${form.writername }</td>
						<td align="center">${form.modifydate }</td>
						<td align="center"><a href="###" onclick="handle('${form.actionurl }','${form.actionname }-${form.name }')">${form.actionname }</a></td>
						<td align="center">
							
							<c:choose>
								<c:when test="${not empty form.deleteurl}">
									<a href="javascript:void(0)" onClick="deleteUnProcess('${form.deleteurl}')">删除</a>
								</c:when>
							
								<c:otherwise>
									<a href="###" ><span style="color: gray;">删除</span></a>
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" />
				</td>
			</tr>
		</table>

	</form>

</body>
</html>
