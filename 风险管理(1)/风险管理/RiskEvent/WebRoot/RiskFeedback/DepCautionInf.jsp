<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    <base href="<%=basePath%>">
    
    <title>本部门警戒信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
  </head>
  
<body style="font-size:12px; margin-top:5px;" onmousemove="javascript:MouseMoveToResize(event);" onmouseup="javascript:MouseUpToResize();">
<form name="depCautionInf" id="depCautionInf" method="post" action="riskFeedback/depCautionInfAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>本部门警戒信息</span></div></div>
<!--<p:page pageTitle="部门名称;风险事件编号;警戒时间"  pageVaule="department.depName;riskEvent;dwTime" id="mytable" dataProvider="depWarnList"
  			actionUrl="riskFeedback/depCautionInfAction"
  			pageNum="10"
  			menu="true"
  			menuAlign="left"				
 ></p:page> 
  -->
  
  
  <div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px;">
        
		<!--<li style="float:left;margin-left:0px;font-size:14px;"> 风险事件编号：<s:select name="eventnumber" theme="simple" list="alleventList" listValue="riskEvent" listKey="riskEvent" class="text" style="width:240px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>-->
   <li style="float:left;margin-left:20px;font-size:14px;"> 警戒年份：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>
        <li style="float:left;margin-left:10px;font-size:14px;"> 至：<input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>   
        </ul>
        <div style="width:100%;float: left;height:30px;text-align: center;margin-top:10px;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
	
		<input type="button" class="querybutton" name="buttonAll" id="buttonAll" value="全部记录" onClick="showAllInfo();" />
		</div>
		</div>
		</div>
		
  <div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' ><thead>
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<th width="10%" align="center" height="30">序号</th>
                		<th width="10%" align="center">部门名称</th>
                		<th width="10%" align="center">风险事件编号</th>
                		<th width="10%" align="center">警戒时间</th>
                		<th width="10%" align="center">计划时间</th>
                		<th width="10%" align="center">警戒原因</th>
                		
						<%int num=0;%>
            		</tr>
             		<tbody id="body_RiskEventInputQuery">
            		<s:iterator value="depWarnList">
            		<tr align="center" >
						
						<td height="25" align="center"><%=++num%></td>
            			<td align="center"><s:property value="department.depName"/></td>
            			<td align="center"><s:property value="riskEvent"/></td>
            			<td align="center"><s:property value="dwTime"/></td>
            			<td align="center"><s:property value="dwPlanTime"/></td>
            			<td align="center"><s:property value="dwReason"/></td>
						
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
				  <div id='pageDIV' style="background-image:url(IconPage/images/bg.png)"><div align="right"> 
                    <a onClick="document.forms[0].action='riskFeedback/depCautionInfAction?current_pagenum=1&amp;pageNum=10';document.forms[0].submit();"><img border="0" title="首页" src="IconPage/images/first.gif"></a> 
                    <a onClick="prepage()"><img border="0" title="上一页" src="IconPage/images/prev.gif"></a> 
                    <img border="0" src="IconPage/images/line.gif"><span>&nbsp;&nbsp;&nbsp;&nbsp;第</span> 
                    <input type="text" id="current_pagenum" style="width: 45px;" value='<s:property value="current_pagenum"/>'> <span>页&nbsp;&nbsp;</span> 
                    <img border="0" src="IconPage/images/hl.png"><span>共 
                  <% 
                  System.out.print(request.getSession().getAttribute("pagecount"));
                   int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString()); 
                     int pg=0; 
                     if(pc%10==0){ pg=pc/10;out.print(pg);} 
                     else {pg=(pc/10)+1;out.print(pg);} 
                  %>页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;<img border="0" src="IconPage/images/line.gif"> 
                  <a onClick="nextpage()"><img border="0" title="下一页" src="IconPage/images/next.gif"></a>&nbsp;&nbsp; 
                  <a onClick="document.forms[0].action='riskFeedback/depCautionInfAction?current_pagenum=<%=pg%>&amp;pageNum=10';document.forms[0].submit();"><img border="0" title="尾页" src="IconPage/images/last.gif"></a>&nbsp;&nbsp; 
                 <img border="0" src="IconPage/images/line.gif">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <a onClick="document.forms[0].action='riskFeedback/depCautionInfAction?current_pagenum='+current_pagenum.value+'&amp;pageNum=10';document.forms[0].submit();"><img border="0" style="cursor: pointer;" title="跳转" src="IconPage/images/load.png"></a>&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="IconPage/images/line.gif"> 
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span></div>
</div>
  </form>
  <script type="text/javascript">
 x=true;
     function prepage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(current_page==0)	return;
    	document.forms[0].action='riskFeedback/depCautionInfAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }
     function nextpage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1+1;   	
    	document.forms[0].action='riskFeedback/depCautionInfAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }

function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		depCautionInf.action="riskFeedback/depCautionQueryAction?current_pagenum=1";
		depCautionInf.submit();
		
	}
}
function query()
{
	depCautionInf.action="riskFeedback/querydepCautionInfAction?current_pagenum=1";
	depCautionInf.submit();
		
}
function showAllInfo()
{
depCautionInf.action="riskFeedback/depCautionInfAction?current_pagenum=1";
		depCautionInf.submit();
		
}
  </script>
  </body>
</html>
