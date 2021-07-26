<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>已办工作</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script src="/RiskEvent/IconPage/page.js" type="text/javascript"></script>
	<script src="/RiskEvent/IconPage/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script>

	<script type="text/javascript">
	
		function submitform()
		{
			document.getElementById("orderbys").value = "";
			//document.getElementById("dateFrom").value = "";
			//document.getElementById("dateTo").value = "";
			WorkQuery.submit();
		}
		
		function prepage()
	    {
	        var s=document.getElementById("actionName").value+"?current_pagenum=";
	    	var current_page = document.getElementById("current_pagenum");
	    	current_page=(current_page.value)*1-1;
	    	if(0==current_page)	return;
	    	WorkQuery.action=s+current_page+"&pageNum=10";
	    	WorkQuery.submit();
	   
	    }
	    function nextpage(maxpage)
	    {
	        var s=document.getElementById("actionName").value+"?current_pagenum=";
	    	var current_page = document.getElementById("current_pagenum");
			if(maxpage == current_page)	return;	
			current_page=(current_page.value)*1+1;  
			if(maxpage < current_page)	return;	
	    	WorkQuery.action=s+current_page+"&pageNum=10";
	    	WorkQuery.submit();
	    }
	    //首页
		function firstpage()
		{
	    	var s=document.getElementById("actionName").value+"?current_pagenum=";
	    	WorkQuery.action=s+1+"&pageNum=10";
	    	WorkQuery.submit();
		}
		//尾页
		function lastpage(pageno)
		{
			var s=document.getElementById("actionName").value+"?current_pagenum=";
	    	WorkQuery.action=s+pageno+"&pageNum=10";
	    	WorkQuery.submit();
		}
		//跳转
		function jumppage(maxpage)
		{
			var s=document.getElementById("actionName").value+"?current_pagenum=";	
			var pageno=document.getElementById("current_pagenum").value;
			
			if(maxpage < pageno || pageno < 1)	return;	
	    	WorkQuery.action=s+pageno+"&pageNum=10";
	    	WorkQuery.submit();
		}
		
		function changeUpDown(sorts)
		{
			var path=document.getElementById(sorts); 
			if(path.src.indexOf("up.png")!=-1) 
			{ 
				path.src="/RiskEvent/IconPage/images/dn.png";
 				//ordersorts=sorts+' desc'; 
 				document.getElementById("orderbys").value = sorts+" DESC";
 				//alert(document.getElementById("orderbys").value);
 				var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10";
				WorkQuery.action=s;
				WorkQuery.submit();
    		} 
			else 
			{
				path.src="/RiskEvent/IconPage/images/up.png"; 
				//ordersorts=sorts+' asc'; 
				document.getElementById("orderbys").value = sorts+" ASC";
				//alert(document.getElementById("orderbys").value);
				var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10";
				WorkQuery.action=s;
				WorkQuery.submit();
 			};
		}
		
		function init()
		{
		
			var orderString = document.getElementById("orderbys").value;
			
			if (orderString !== null || orderString !== undefined || orderString !== '') 
			{
				var arr = new Array();
				arr = orderString.split(" ");
				var path=document.getElementById(arr[0]);
				if(arr[1].toLowerCase() == "DESC".toLowerCase())
				{
					path.src="/RiskEvent/IconPage/images/dn.png";
				}
				else 
				{
					path.src="/RiskEvent/IconPage/images/up.png";
				}
			} 
			
		}
		
		function reSetmess() 
		{
			document.getElementById("dateFrom").value = "";
			document.getElementById("dateTo").value = "";
			//submitform();
			$("#messagestate").get(0).selectedIndex = 0;
		}
		
		function handle(url,tabname) 
		{
			window.parent.openTab(url,tabname);
		}
		
		function readmsg(obj,messageId)
		{
			var url = "default/reportMessageAction_updateState";
			var params="messageId="+ messageId;
			$.ajax({
		              url: url,
		              type: "post",
		              dateType: "json",
		              data: params,
		              contentType: "application/x-www-form-urlencoded",			//很重要
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
		
	</script>
	
</head>

<body style="font-size:12px; margin-top:5px;" onload="init()">

	<form action="default/reportMessageAction_getReportMessage" name="WorkQuery" id="WorkQuery" method="post">

		<div class="toptitle">
			<div class="toptitleleft"></div>
			<div class="topttileright"></div>
			<div class="toptitlemiddle">
				<span>已办工作</span>
			</div>
		</div>
		<div class="queryDiv">
				<div style="display: inline text-align: center">
					<ul style="list-style-type: none; width: inherit; margin-top: 5px;">
						
						<li style="float:left;margin-left:10px;font-size:14px;"> 
							消息状态：
							<select name="messagestate" id="messagestate" style="width: 150px" onchange="submitform()">
								<option value="">--请选择--</option>
								<option value="1" <c:if test="${state=='1' }">selected</c:if>>已读消息</option>
		       					<option value="0" <c:if test="${state=='0' }">selected</c:if>>未读消息</option>
							</select>
						</li>
						
						<li style="float:left;margin-left:20px;font-size:14px;">
							日期：
							<input name="dateFrom" id="dateFrom" class="Wdate" style="width:150px;" value="${dateFrom }" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
						</li>
						<li style="float:left;margin-left:5px;font-size:14px;">
							至：
							<input name="dateTo" id="dateTo" class="Wdate" style="width:150px;" value="${dateTo }" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
						</li>
					</ul>
					<div style="width: 100%; float: left; height: 30px; text-align: center; margin-top: 10px">
        				<input type="button" style="width:70px;height: 24px;" name="buttonQuery" id="buttonQuery" value="查询" onClick="submitform();" />					     
						<input type="button" style="width:70px;height: 24px;" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/>
					</div>
				</div>
			</div>
		<div id='pagetable'>
			<table id='mytable' class='tablestyle' width='100%' height='100%'>
				<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
					<td width="10%" align="center" height="30">序号</td>
					<td width="10%" align="center">类别</td>
					<td width="20%" align="center">名称</td>
					<td width="30%" align="center">消息内容</td>
					<td width="5%" align="center">状态</td>
					<td width="10%" align="center">日期</td>
					<!-- <td width="5%" align="center">操作</td>  -->
					<%
						int num = 0;
					%>
				</tr>
				<c:forEach items="${messagelist}" var="message">
					<tr>
						<td align="center"><%=++num%></td>
						<td align="center">${message.rmCategoryName }</td>
						<td align="center">${message.rmFormName }</td>
						<td align="center">${message.rmDetail }</td>
						<c:choose>
							<c:when test="${fn:trim(message.rmState) eq '0' }">
								<td align="center" title="单击设为已读">
									<a href="###" onclick="readmsg(this,'${message.rmId}')">未读</a>
								</td>
							</c:when>
							
							<c:otherwise>
								<td align="center">已读</td>
							</c:otherwise>
							
						</c:choose>
						
						<td align="center">${message.rmDate }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="6"><img src="/RiskEvent/images/main/spacer.gif"
					width="1" height="1" />
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
		<input type="hidden" name="actionName" id="actionName" value="${actionName}"/>
		<input type="hidden" name="orderbys" id="orderbys" value="${orderbys }" />
	</form>

</body>
</html>
