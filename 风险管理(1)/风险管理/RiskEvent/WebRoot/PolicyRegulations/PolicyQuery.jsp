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
    
    <title>政策法规查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<style>
.filestyle{
padding:0px;
width:100%;
margin-left:0px;
}
.filestyle ul{
 margin-left:5px;
 margin-top:10px;
 margin-bottom:8px;
 border-bottom:1px dotted #818181;
 list-style-type:none;
 text-align:left;
}
.filestyle li{
padding:3px;
margin-left:10px;
}
.title{
font-size:15px;
color:#4c4c4c;
}
.title:hover{
color:#2e9cee;
}
.title:VISITED{
 color:#2e9cee;}
.remark{
color:#818181;}
.author{
color:#a1a1a1;}
</style>
  </head>
  
  <body style="font-size:12px;">
    <form id="policyQuery" name="policyQuery" method="post" action="PolicyRegulations/queryLaw" >
    <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>政策法规查询</span></div></div>
       <div class="queryDiv">
         <span>政策法规名称:</span>
		<input height="30" name="fileTitle" id="fileTitle" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/>
		<img height="25" align="middle" src="images/mag.png" onClick="search()"/>
		</div>
      <div class="MainDiv">
        <div class="filestyle">
        <s:iterator value="queryList">
        <ul>

        <li><a href="download1.action?id=<s:property value="fileId"/>" class="title"><s:property value="filetitle"/></a><a href="download1.action?id=<s:property value="fileId"/>" ><img width="30" border="0" height="30" title="下载" src="/RiskEvent/frame/icon/Cardboard Box Download.png"/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="remark"><s:property value="fileremark"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <span class="author">来自: </span><span class="author"><s:property value="users.userName"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="author">上传时间:<s:property value="fileDate"/></span>
        </li>
        </ul>
        </s:iterator>       
        </div>
        </div>
        <div id='pageDIV' style="background-image:url(IconPage/images/bg.png)">
<a onClick="document.forms[0].action='PolicyRegulations/queryLaw?current_pagenum=1&pageNum=10';document.forms[0].submit();"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
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
<a onClick="document.forms[0].action='PolicyRegulations/queryLaw?current_pagenum=<%=pg%>&pageNum=10';document.forms[0].submit();"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>&nbsp;&nbsp;
<img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
<a onClick="document.forms[0].action='PolicyRegulations/queryLaw?current_pagenum='+current_pagenum.value+'&pageNum=10';document.forms[0].submit();"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div>
        
     </form>
     <script>
     function prepage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(current_page==0)	return;

    	document.forms[0].action='PolicyRegulations/queryLaw?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();

     }
     function nextpage(){
    	var current_page = document.getElementById("current_pagenum");

    	current_page=(current_page.value)*1+1;   	
    	document.forms[0].action='PolicyRegulations/queryLaw?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }
     
     function search(){
	policyQuery.action="dataUnit/queryLawbyTitle?current_pagenum=1";
	policyQuery.submit();
}
     </script>
  </body>
</html>
