<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>报告台账</title>

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
				var pageUrl = "&date=" + date;
				TaskQuery.action="riskReport/riskReportAction_getReport?current_pagenum=1"; 
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
			    $("#state").get(0).selectedIndex = 0;
			    document.getElementById("reportname").value = "";
			}
			
			function EnterPress() 
			{
	
				var e = e || window.event;
				if(e.keyCode == 13)
				{
					query();
				};
			}
			
			function handle(reportId,reportName)
			{
				var url = "/RiskEvent/riskReport/reportReadAction?reportId=" + reportId;
				window.parent.openTab(url,"档案袋-" + reportName);
			}
			
     	</script>
		
	</head>

	<body style="font-size: 12px; margin-top: 5px;">
		<form id="TaskQuery" name="TaskQuery" method="post" action="riskReport/riskReportAction_getReport">

			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle">
					<span>报告台账</span>
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
							发起时间：
							<input name="date" id="date" value="${date}" class="Wdate" style="width: 150px;" value="" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM'})" />
						</li>
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							是否完成编写：
							<select name="state" id="state" style="width: 150px">
								<option value="">--请选择--</option>
		       					<option value="0" <c:if test="${state=='0'}">selected</c:if>>未完成</option>
		       					<option value="1" <c:if test="${state=='1'}">selected</c:if>>已编写</option>
							</select>
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
							最后处理时间
						</td>
						<td width="10%" align="center">
							发起人
						</td>
						<td width="10%" align="center">
							发起时间
						</td>
						<td width="10%" align="center">
							流转状态
						</td>
						
					</tr>
					
					<c:forEach items="${taskList}" var="task">
             			<tr title="双击查看详细信息" onDblClick="handle('${task.rerReportId}','${task.rerReportName}')">
            				<td align="center">${task.rerReportName }</td>
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
            						<c:when test="${task.retState=='1' && task.retFlowId=='HZBGSP'}">
            							已编写
            						</c:when>
            						<c:otherwise>
            							未完成
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
