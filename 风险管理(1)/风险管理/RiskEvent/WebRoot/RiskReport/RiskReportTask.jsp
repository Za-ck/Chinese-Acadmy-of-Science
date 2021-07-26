<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>任务台账</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" />
		<script src="/RiskEvent/IconPage/page.js" type="text/javascript"></script>
		<script src="/RiskEvent/IconPage/jquery-1.3.2.min.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			function query()
			{
				var date = document.getElementById("date").value;
				var flowId = $("#flowrules option:selected").val();
				var pageUrl = "&date=" + date + "&flowId=" + flowId;
				TaskQuery.action="riskReport/riskReportProcessAction_getTask?current_pagenum=1"; 
				TaskQuery.submit();
			}
		    function prepage()
		    {
		    	var pageUrl = document.getElementById("pageUrl").value;
		        var s=document.getElementById("actionName").value+"?current_pagenum=";
		    	var current_page = document.getElementById("current_pagenum");
		    	current_page=(current_page.value)*1-1;
		    	if(0==current_page)	return;
		    	TaskQuery.action=s+current_page+"&pageNum=10";
		    	TaskQuery.submit();
		   
		    }
		    function nextpage(maxpage)
		    {
		    	var pageUrl = document.getElementById("pageUrl").value;
		        var s=document.getElementById("actionName").value+"?current_pagenum=";
		    	var current_page = document.getElementById("current_pagenum");
				if(maxpage == current_page)	return;	
				current_page=(current_page.value)*1+1;  
				if(maxpage < current_page)	return;	
				
		    	TaskQuery.action=s+current_page+"&pageNum=10";
		    	TaskQuery.submit();
		    }
		    //首页
			function firstpage()
			{
				var pageUrl = document.getElementById("pageUrl").value;
		    	var s=document.getElementById("actionName").value+"?current_pagenum=";
		    	TaskQuery.action=s+1+"&pageNum=10";
		    	TaskQuery.submit();
			}
			//尾页
			function lastpage(pageno)
			{
				var pageUrl = document.getElementById("pageUrl").value;
				var s=document.getElementById("actionName").value+"?current_pagenum=";
		    	TaskQuery.action=s+pageno+"&pageNum=10";
		    	TaskQuery.submit();
			}
			//跳转
			function jumppage(maxpage)
			{
				var pageUrl = document.getElementById("pageUrl").value;
				var s=document.getElementById("actionName").value+"?current_pagenum=";	
				var pageno=document.getElementById("current_pagenum").value;
				
				if(maxpage < pageno || pageno < 1)	return;	
		    	TaskQuery.action=s+pageno+"&pageNum=10";
		    	TaskQuery.submit();
			}
			function reSetmess()
			{
			    document.getElementById("date").value = "";
			    $("#flowrules").get(0).selectedIndex = 0;
			    $("#state").get(0).selectedIndex = 0;
			    document.getElementById("reportname").value = "";
			    $("#reIndep").get(0).selectedIndex = 0;
			}
			
			function EnterPress() 
			{
	
				var e = e || window.event;
				if(e.keyCode == 13)
				{
					query();
				};
			}
			
			function openTab(url,tabname) 
			{
			
				window.parent.openTab(url,tabname);
				
			}
			
			//判断用户角色，如果各个部门的员工，则将编写部门框设置为disabled
			$(function(){
				var cur_userId = "${cur_userId}";
				if(cur_userId != '1914'){
					$("#reIndep").attr('disabled','disabled');
				}
			});
     	</script>
		
	</head>

	<body style="font-size: 12px; margin-top: 5px;">
		<form id="TaskQuery" name="TaskQuery" method="post" action="riskReport/riskReportProcessAction_Task">

			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle">
					<span>任务台账</span>
				</div>
			</div>
			<div class="queryDiv">
				<div style="display: inline text-align: center">
					<ul style="list-style-type: none; width: inherit; margin-top: 5px;">
						
						<li style="float: left; margin-left: 10px; font-size: 14px;">
							报告名称：
							<input name="reportname" id="reportname" class="text" value="${reportname}" style="width: 300px;" onkeypress="EnterPress(event)" onkeydown="EnterPress()"/>
						</li>
						
						<li style="float: left; margin-left: 10px; font-size: 14px;">
							最后处理时间：
							<input name="date" id="date" value="${date}" class="Wdate" style="width: 150px;" value="" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM'})" />
						</li>
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							所属流程：
							<select name="flowrules" id="flowrules" style="width: 150px">
								<option value="">--请选择--</option>
		       					<c:forEach items="${flows}" var="flow">
     								<option value="${flow.frFlowId}" <c:if test="${flowId==flow.frFlowId}">selected</c:if>>${flow.frFlowName}</option>
     							</c:forEach>
							</select>
						</li>
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							是否完成：
							<select name="state" id="state" style="width: 150px">
								<option value="">--请选择--</option>
		       					<option value="0" <c:if test="${state=='0'}">selected</c:if>>未完成</option>
		       					<option value="1" <c:if test="${state=='1'}">selected</c:if>>已完成</option>
							</select>
						</li>
						<li style="float:left; margin-left:10px;font-size:14px;">
							编写部门：
							<s:select  id="reIndep" name="reIndep" theme="simple" list="alldepList" listValue="depName" listKey="depName" class="text" style="width:240px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
						</li>
					</ul>
					
					<div style="width: 100%; float: left; height: 30px; text-align: center; margin-top: 10px">
						<input type="button" style="width:70px;height: 24px;display: inline" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />
						<input type="button" style="width:70px;height: 24px;display: inline" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();" />
					</div>
				</div>
			</div>
			<div id='pagetable'>
				<table id='mytable' class='tablestyle' width='100%' height='100%'>
					<tr style="background-image: url(/RiskEvent/IconPage/images/thbg.png)">
					
						<td width="10%" align="center">
							报告名称
						</td>
						<td width="10%" align="center">
							所属流程
						</td>
						<td width="10%" align="center">
							编写人
						</td>
						<td width="10%" align="center">
							编写部门
						</td>
						<td width="10%" align="center">
							流转状态
						</td>
						<td width="10%" align="center">
							最后处理时间
						</td>
						<td width="10%" align="center">
							发起人
						</td>
						<td width="10%" align="center">
							发起时间
						</td>
						<td width="10%" align="center">
							操作
						</td>
						
					</tr>
					
					<c:forEach items="${taskList}" var="task">
             			<tr>
            				<td align="center">${task.rerReportName }</td>
            				<td align="center">${task.frFlowName }</td>
            				<td align="center">${task.fiWriterName }</td>
            				<td align="center">${task.fiWriterDepName }</td>
            				<td align="center">
            					<a href="${task.rfFlowActionName}_ReadFlow?reportId=${task.rerReportId }&taskId=${task.retTaskId}" target="_blank">${task.fiStatements }</a>
            				</td>
            				<td align="center">
            					<c:choose>
            						<c:when test="${not empty task.retProcessTime}">
            							${task.retProcessTime }
            						</c:when>
            						<c:otherwise>
            							${task.retLastTime }
            						</c:otherwise>
            					</c:choose>
            					
            				</td>
            				<td align="center">${task.rerPromoterName }</td>
            				<td align="center">${task.rerDate }</td>
							<td align="center">
								<c:choose>
            						<c:when test="${task.retState=='1'}">
            							<a href="###" onclick="openTab('${task.rfFlowActionName}_ReadReport?reportId=${task.rerReportId }&taskId=${task.retTaskId}','查看详情-${task.rerReportName }')">查看详情</a>
            						</c:when>
            						<c:otherwise>
            							<a href="###" onclick="openTab('${task.rfActionUrl}?reportId=${task.rerReportId }&taskId=${task.retTaskId}','${task.rfAction }-${task.rerReportName }')">${task.rfAction}</a>
            						</c:otherwise>
            					</c:choose>
                    			
                    			
                    		</td>
            			</tr>
             		</c:forEach>
					
				</table>
			</div>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="6">
						<img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" />
					</td>
				</tr>
			</table>
			<div id='pageDIV' style="background-image: url(IconPage/images/bg.png)">
				<a onClick="firstpage()"><img border='0' title='首页' src='/RiskEvent/IconPage/images/first.gif'/></a>
				<a onClick="prepage()"><img border='0' title='上一页' src='/RiskEvent/IconPage/images/prev.gif'/></a>
				<img border='0' src='/RiskEvent/IconPage/images/line.gif' />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;第</span>
				<input id='current_pagenum' style='width: 45px' type='text' value="${current_pagenum}" />
				<span>页&nbsp;&nbsp;</span>
				<img border='0' src='/RiskEvent/IconPage/images/hl.png'/>
				<span>共 ${pg}
				页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
				<img border='0' src='/RiskEvent/IconPage/images/line.gif'/>
				<a onClick="nextpage(${pg})"><img border='0' title='下一页' src='/RiskEvent/IconPage/images/next.gif'/></a>&nbsp;&nbsp;
				<a onClick="lastpage(${pg})"><img border='0' title='尾页' src='/RiskEvent/IconPage/images/last.gif'/></a>&nbsp;&nbsp;
				<img border='0' src='/RiskEvent/IconPage/images/line.gif' />&nbsp;&nbsp;&nbsp;&nbsp;
				<a onClick="jumppage(${pg})"><img border='0' style='cursor: pointer;' title='跳转' src='/RiskEvent/IconPage/images/load.png' /></a>&nbsp;&nbsp;&nbsp;&nbsp;
				<img border='0' src='/RiskEvent/IconPage/images/line.gif' />&nbsp;&nbsp;&nbsp;&nbsp;
				<span>共有&nbsp;&nbsp;${pagecount }&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;${current_pagenum }/${pg}页</span>
			</div>
			<input type="hidden" name="pageUrl" id="pageUrl" value="${pageUrl}" />
			<input type="hidden" name="actionName" id="actionName" value="${actionName}"/>
		</form>
		
	</body>
</html>
