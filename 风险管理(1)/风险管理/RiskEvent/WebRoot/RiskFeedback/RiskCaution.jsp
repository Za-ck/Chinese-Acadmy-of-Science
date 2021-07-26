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
    
    <title>风险警戒事件信息</title>
    
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

<form id="riskCaution" name="riskCaution" method="post" action="riskFeedback/RiskCautionAction" onSubmit="return checkSubmit()">

<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险警戒事件信息</span></div></div>
<div class="queryDiv">
<div style="display:;text-align: center">
<ul style="list-style-type:none;width:inherit;margin-top:5px;">
<!--  <li style="float:left;margin-left:5px;">风险编号:<s:select name="risknumber" theme="simple" list="risknumberList" listValue="nameValue" listKey="nameValue" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>-->
<li style="float:left;margin-left:20px;font-size:14px;">部门名称：<s:select name="riskDeps" theme="simple" list="alldepList" listValue="depName" listKey="depId" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>
<li style="float:left;margin-left:5px;">
<input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />
</li>
</ul>
</div>
</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' ><thead>
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<th width="10%" align="center" height="30">序号</th>
                		<th width="10%" align="center">风险编号</th>
                		<th width="10%" align="center">风险名称</th>
                		<th width="10%" align="center">录入部门</th>
                		<th width="10%" align="center">计划时间</th>
                		<th width="10%" align="center">录入时间</th>
                		<th width="10%" align="center">操作</th>
						<%int num=0;%>
            		</tr>
             		<tbody id="body_RiskEventInputQuery">
            		<s:iterator value="riskCautionList">
            		<tr title="双击查看风险事件信息" align="center" onDblClick="window.location.href='riskInput/REIQReadAction?reId=<s:property value='id.reId'/>&backFlag=inputQuery'">
						
						<td height="25" align="center"><%=++num%></td>
            			<td align="center"><s:property value="id.reId"/></td>
            			<td align="center"><s:property value="id.riskName"/></td>
            			<td align="center"><s:property value="id.depName"/></td>
            			<td align="center"><s:property value="id.rmPlandate"/></td>
            			<td align="center"><s:property value="id.reDate"/></td>
						<td align="center">
                    		<input id="jinggao" type="button" name="button" value="警告" class="button" onClick="caution(this)"/> 
                    	</td>
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
                    <a onClick="document.forms[0].action='riskFeedback/RiskCautionAction?current_pagenum=1&pageNum=10';document.forms[0].submit();"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
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
                  <a onClick="nextpage()"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>&nbsp;&nbsp;
                  <a onClick="document.forms[0].action='riskFeedback/RiskCautionAction?current_pagenum=<%=pg%>&pageNum=10';document.forms[0].submit();"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>&nbsp;&nbsp;
                 <img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a onClick="document.forms[0].action='riskFeedback/RiskCautionAction?current_pagenum='+current_pagenum.value+'&pageNum=10';document.forms[0].submit();"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div>
        
     </form>
     <script>
     <%
  String params=request.getParameter("operation");
  String[] temps=null;
  String operation="",result="";
  if(params!=null){
	 temps=params.split("\\*");	 
      operation=temps[0];
      result=temps[1].split("=")[1];
 }
%>
var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if((operation=="handle")&&(result=="success")) alert("警戒成功!");
	else if(operation=="handle"&&result=="fail") alert("警戒失败!");
}

       x=true;
     function prepage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(current_page==0)	return;
    	document.forms[0].action='riskFeedback/RiskCautionAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }
     function nextpage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1+1;   	
    	document.forms[0].action='riskFeedback/RiskCautionAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }
function caution(_this){
if(x){
	if(confirm('确认要警告吗？'))
	{
		var objTR=_this.parentNode.parentNode;
		var obj=objTR.getElementsByTagName("td");
		var my=obj[1].innerHTML;
		riskCaution.action="riskFeedback/RiskTellAction?rcId="+my;
		riskCaution.submit();
		x=false;
	}
 }
 else{
 	document.all.jinggao.disabled=true;
 }
}
function checkSubmit(){
document.all.jinggao.disabled=true;
return true;
}
function query(){
	riskCaution.action="riskFeedback/RiskCautionqueryAction?current_pagenum=1";
	riskCaution.submit();
}

     </script>

</body>
</html>
