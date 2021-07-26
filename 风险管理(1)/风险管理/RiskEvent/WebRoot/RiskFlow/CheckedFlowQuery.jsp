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
    
    <title>已处理风险事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
	
</head>

<body style="font-size:12px; margin-top:5px;">

<form id="CheckedriskQuery" name="CheckedriskQuery" method="post" action="riskFlow/RiskCheckedAction" >

<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>已处理风险事件</span></div></div>
<div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px;">
 
        <li style="float:left;margin-left:5px;font-size:14px;"> 处理时间：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:130px;" value="<s:property value="dateFrom"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>
        <li style="float:left;margin-left:5px;font-size:14px;"> 至：<input name="dateTo" id="dateTo" class="Wdate" style="width:130px;"  value="<s:property value="dateTo"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>   
        </ul>
        <div style="width:100%;float: left;height:30px;text-align: center;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
		<input type="button" class="right-buttonShort" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/></div>
		</div>
		</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="4%" align="center" height="30">序号</td>
                		<td width="10%" align="center">风险事件编号</td>
                		<td align="center">风险事件名称</td>
                		<td width="10%" align="center">风险名称</td>
                		<td width="10%" align="center">录入部门</td>
                		<td width="10%" align="center">状态</td>
                		<td width="10%" align="center">填写人</td>
                		<td width="10%" align="center">填写日期</td>
                		<td width="10%" align="center">操作日期</td>
						<%int num=0;%>
            		</tr>
             		<tbody id="body_RiskEventInputQuery">
            		<s:iterator value="rcheckedList">
            		<tr title="双击查看风险事件信息" align="center" onDblClick="handle('<s:property value='reId'/>','<s:property value='eventname'/>')">
						
						<td height="25" align="center"><%=++num%></td>
            			<td align="center"><s:property value="reId"/></td>
            			<td align="center"><s:property value="eventname"/></td>
            			<td align="center"><s:property value="riskname"/></td>
            			<td align="center"><s:property value="indep"/></td>
            			<td align="center"><a href="riskFlow/RiskFlowChart?riskId=<s:property value='riskId'/>&resId=<s:property value="reId"/>&reState=<s:property value='status'/>" target="_blank">
                      <s:if test='status=="*"'>
                                                              已发布</s:if>
            			<s:elseif test='react=="0"'>
            			<s:if test='status=="0"'>
                                                              未修改</s:if>
                        <s:else>
            			未通过
            			</s:else>
            			</s:elseif>          
            			<s:else>
            			<s:if test='status=="1"'>
                                                              已提交</s:if>
                        <s:else>
            			等待审核中...
            			</s:else>
            			</s:else>
            			</a>
            			</td>
            			<td align="center"><s:property value="creator"/></td>
            			<td align="center"><s:property value="indate"/></td>
            			<td align="center"><s:property value="time"/></td>
            		</tr>
            		</s:iterator>
            		</tbody>
        		</table>
        		</div>
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				  <div id='pageDIV' style="background-image:url(IconPage/images/bg.png)">
                    <a onClick="firstpage()"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <img border='0' src='IconPage/images/line.gif'/><span>&nbsp;&nbsp;&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<s:property value="current_pagenum"/>"/> <span>页&nbsp;&nbsp;</span>
                    <img border='0' src='IconPage/images/hl.png'/><span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/10;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
                  <a onClick="nextpage(<%=pg%>)"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>&nbsp;&nbsp;
                  <a onClick="lastpage(<%=pg%>)"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>&nbsp;&nbsp;
                 <img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a onClick="jumppage(<%=pg%>)"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div>
                 <input type="text" style="visibility:hidden"  name="actionName" id="actionName" value="<s:property value="actionName"/>"/>
                 
     </form>
<script>
function query()
{
	CheckedriskQuery.action="riskFlow/CheckedAdvancedQuery?current_pagenum=1"; 
	CheckedriskQuery.submit();
	//document.getElementById('dateFlag').value="";
}
     function prepage(){
        var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	CheckedriskQuery.action=s+current_page+"&pageNum=10";
    	CheckedriskQuery.submit();
   
     }
     function nextpage(maxpage){
        var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;  
		if(maxpage < current_page)	return;	
		
    	CheckedriskQuery.action=s+current_page+"&pageNum=10";
    	CheckedriskQuery.submit();
    	
    	
     }
     //首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	CheckedriskQuery.action=s+1+"&pageNum=10";
    	CheckedriskQuery.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	CheckedriskQuery.action=s+pageno+"&pageNum=10";
    	CheckedriskQuery.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	CheckedriskQuery.action=s+pageno+"&pageNum=10";
    	CheckedriskQuery.submit();
}
function reSetmess()
{
    document.getElementById('dateFrom').value="";
	 document.getElementById('dateTo').value="";
};

function handle(reId,eventname) 
{
	var url = "riskInput/REIQReadAction?reId=" + reId;
	window.parent.openTab(url,"查看详情-" + eventname);
};
     </script>

</body>
</html>
